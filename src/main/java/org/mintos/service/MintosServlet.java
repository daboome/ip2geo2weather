package org.mintos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;
import org.mintos.proxy.GeoProxy;
import org.mintos.proxy.WeatherProxy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class MintosServlet extends HttpServlet {

    private static final String IP_NOT_PROVIDED_IN_HEADER_ERR_MSG = "Ip was not provided in HEADER";

    private final GeoProxy geoProxy = new GeoProxy();
    private final WeatherProxy weatherProxy = new WeatherProxy();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        CompletableFuture
                .supplyAsync(() -> {
                    if (request.getHeader("X-Forwarded-For") == null) {
                        throw new IllegalStateException(IP_NOT_PROVIDED_IN_HEADER_ERR_MSG);
                    } else {
                        return request.getHeader("X-Forwarded-For");
                    }
                })
                .exceptionally(handleWithError(httpServletResponse, IP_NOT_PROVIDED_IN_HEADER_ERR_MSG))
                .thenCompose(geoProxy::getGeoResponse)
                .exceptionally(handleWithError(httpServletResponse, new GeoResponse()))
                .thenCompose(weatherProxy::getWeatherResponse)
                .exceptionally(handleWithError(httpServletResponse, new WeatherResponse()))
                .thenAccept(weatherResponse -> {
                    try {
                        httpServletResponse.setContentType("application/json");
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                        writer.writeValue(httpServletResponse.getOutputStream(), weatherResponse);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .join();
    }

    private <T> Function<Throwable, T> handleWithError(HttpServletResponse httpServletResponse, T returnObject) {
        return throwable -> {
            try {
                httpServletResponse.setContentType("application/text");
                httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                writer.writeValue(httpServletResponse.getOutputStream(), throwable.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return returnObject;
            }
        };
    }
}
