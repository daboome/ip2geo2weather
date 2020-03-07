package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class WeatherProxy extends ServiceProxy {

    final String weatherApiUrl;
    final String weatherApiKey;

    public WeatherProxy() {
        weatherApiUrl = conf.getString("mintos.weather-api.url");
        weatherApiKey = conf.getString("mintos.weather-api.key");
    }

    public CompletableFuture<WeatherResponse> getWeatherResponse(final GeoResponse geoResponse) {
        return CompletableFuture.supplyAsync(() -> {
            Double latitude = geoResponse.getLatitude();
            Double longitude = geoResponse.getLongitude();
            final URIBuilder uriBuilder;
            try {
                uriBuilder = new URIBuilder(weatherApiUrl)
                        .addParameter("key", weatherApiKey)
                        .addParameter("q", latitude.toString().concat(",").concat(longitude.toString()));
                return get(WeatherResponse.class, uriBuilder);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
