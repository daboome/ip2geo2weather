package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.IdentifiableEntity;
import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;
import org.mintos.util.HibernateUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class WeatherProxy extends ServiceProxy {
    // Mutable for the sake of unit testing
    private Double failureRate;

    private static final String WEATHER_SERVICE_NOT_AVAILABLE_ERR_MSG = "Weather service not available";

    final String weatherApiUrl;
    final String weatherApiKey;

    public WeatherProxy() {
        failureRate = conf.getDouble("mintos.failure-rate.weather");
        weatherApiUrl = conf.getString("mintos.weather-api.url");
        weatherApiKey = conf.getString("mintos.weather-api.key");
    }

    public CompletableFuture<WeatherResponse> getWeatherResponse(final GeoResponse geoResponse) {
        CompletableFuture<WeatherResponse> result = new CompletableFuture<>();
        if (Math.random() < failureRate) {
            result.completeExceptionally(new IllegalStateException(WEATHER_SERVICE_NOT_AVAILABLE_ERR_MSG));
        } else {
            result = CompletableFuture.supplyAsync(() -> {
                Double latitude = geoResponse.getLatitude();
                Double longitude = geoResponse.getLongitude();
                final URIBuilder uriBuilder;
                try {
                    uriBuilder = new URIBuilder(weatherApiUrl)
                            .addParameter("key", weatherApiKey)
                            .addParameter("q", latitude.toString().concat(",").concat(longitude.toString()));
                    WeatherResponse weatherResponse = get(WeatherResponse.class, uriBuilder);
                    IdentifiableEntity identifiableEntity = new IdentifiableEntity(weatherResponse);
                    identifiableEntity.setId(geoResponse.getId());
                    identifiableEntity.setGeoResponse(geoResponse);
                    identifiableEntity.setIpAddress(geoResponse.getIpAddress());
                    HibernateUtil.INSTANCE.saveOrUpdateInTransaction(identifiableEntity);
                    return weatherResponse;
                } catch (URISyntaxException | IOException e) {
                    throw new IllegalStateException(WEATHER_SERVICE_NOT_AVAILABLE_ERR_MSG);
                }
            });
        }
        return result;
    }

    public static String getWeatherServiceNotAvailableErrMsg() {
        return WEATHER_SERVICE_NOT_AVAILABLE_ERR_MSG;
    }

    // Mutating method for the sake of unit testing
    public void setFailureRate(Double failureRate) {
        this.failureRate = failureRate;
    }
}
