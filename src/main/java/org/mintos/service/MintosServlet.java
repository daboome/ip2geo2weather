package org.mintos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;
import org.mintos.proxy.GeoProxy;
import org.mintos.proxy.WeatherProxy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.function.Consumer;

public class MintosServlet extends HttpServlet {

    private final GeoProxy geoProxy = new GeoProxy();
    private final WeatherProxy weatherProxy = new WeatherProxy();
    private final ObjectMapper objectMapper = new ObjectMapper();
    protected final ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> headerOptional = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
        headerOptional.ifPresent(getStringConsumer(response));
    }

    private Consumer<String> getStringConsumer(HttpServletResponse response) {
        return ipString -> {
            try {
                GeoResponse geoResponse = geoProxy.getGeoResponse(ipString);
                WeatherResponse weatherResponse = weatherProxy.getWeatherResponse(geoResponse.getLatitude().toString(), geoResponse.getLongitude().toString());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_OK);
                writer.writeValue(response.getOutputStream(), weatherResponse);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        };
    }
}
