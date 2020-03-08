package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class WeatherProxy extends ServiceProxy {

    private static final String WEATHER_SERVICE_NOT_AVAILABLE_ERR_MSG = "Weather service not available";

    final String weatherApiUrl;
    final String weatherApiKey;

    public WeatherProxy() {
        weatherApiUrl = conf.getString("mintos.weather-api.url");
        weatherApiKey = conf.getString("mintos.weather-api.key");
    }

    public CompletableFuture<WeatherResponse> getWeatherResponse(final GeoResponse geoResponse) {
        CompletableFuture<WeatherResponse> result = new CompletableFuture<>();
        if (Math.random() < 0.5) {
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
                    return get(WeatherResponse.class, uriBuilder);
                } catch (URISyntaxException | IOException e) {
                    throw new IllegalStateException(WEATHER_SERVICE_NOT_AVAILABLE_ERR_MSG);
                }
            });
        }
        return result;
    }
}
