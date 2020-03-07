package org.mintos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.mintos.model.weather.WeatherResponse;
import org.mintos.proxy.GeoProxy;
import org.mintos.proxy.WeatherProxy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public class MintosServlet extends HttpServlet {

    private final GeoProxy geoProxy = new GeoProxy();
    private final WeatherProxy weatherProxy = new WeatherProxy();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> headerOptional = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
        headerOptional.ifPresent(getStringConsumer(response));
    }

    private Consumer<String> getStringConsumer(HttpServletResponse httpServletResponse) {
        return ipString -> {
            geoProxy.getGeoResponse(ipString)
                    .thenCompose(weatherProxy::getWeatherResponse)
                    .thenAccept(getWeatherResponseConsumer(httpServletResponse))
                    .join();

        };
    }

    private Consumer<WeatherResponse> getWeatherResponseConsumer(HttpServletResponse httpServletResponse) {
        return weatherResponse -> {
            try {
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                writer.writeValue(httpServletResponse.getOutputStream(), weatherResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
