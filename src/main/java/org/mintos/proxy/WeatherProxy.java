package org.mintos.proxy;

import org.apache.http.client.utils.URIBuilder;
import org.mintos.model.weather.WeatherResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeatherProxy extends ServiceProxy {

    final String weatherApiUrl;
    final String weatherApiKey;

    public WeatherProxy() {
        weatherApiUrl = conf.getString("mintos.weather-api.url");
        weatherApiKey = conf.getString("mintos.weather-api.key");
    }

    public WeatherResponse getWeatherResponse(final String lat, final String lon) throws URISyntaxException, IOException {
        final URIBuilder uriBuilder = new URIBuilder(weatherApiUrl)
                .addParameter("key", weatherApiKey)
                .addParameter("q", lat.concat(",").concat(lon));
        return get(WeatherResponse.class, uriBuilder);
    }
}
