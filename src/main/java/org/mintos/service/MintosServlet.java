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
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MintosServlet extends HttpServlet {

    private static final String IP_NOT_PROVIDED_IN_HEADER_ERR_MSG = "Ip was not provided in header";
    private static final String IP_ADDRESS_HEADER_KEY = "X-Forwarded-For";
    // Deliberately NOT final for the sake of unit testing (to provide/inject proxies with mocks with adjustable failure rates)
    private GeoProxy geoProxy = new GeoProxy();
    private WeatherProxy weatherProxy = new WeatherProxy();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        CompletableFuture
                .supplyAsync(() -> Optional.ofNullable(request.getHeader(IP_ADDRESS_HEADER_KEY))
                        .orElseThrow(() ->  new IllegalStateException(IP_NOT_PROVIDED_IN_HEADER_ERR_MSG)))
                .thenCompose(geoProxy::getGeoResponse)
                .thenCompose(weatherProxy::getWeatherResponse)
                .whenComplete(handleWithError(httpServletResponse))
                .join();
    }

    private BiConsumer<WeatherResponse, Throwable> handleWithError(HttpServletResponse httpServletResponse) {
        return (weatherResponse, throwable) -> {
            if (throwable == null) {
                flushWithResponse(httpServletResponse, "application/json", HttpServletResponse.SC_OK, weatherResponse);
            } else {
                flushWithResponse(httpServletResponse, "application/text", HttpServletResponse.SC_SERVICE_UNAVAILABLE, throwable.getMessage());
            }
        };
    }

    private <T> void flushWithResponse(HttpServletResponse httpServletResponse, String contentType, Integer status, T object) {
        try {
            httpServletResponse.setContentType(contentType);
            httpServletResponse.setStatus(status);
            writer.writeValue(httpServletResponse.getOutputStream(), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIpAddressHeaderKey() {
        return IP_ADDRESS_HEADER_KEY;
    }
}
