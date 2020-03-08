package org.mintos.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;
import org.mintos.proxy.GeoProxy;
import org.mintos.proxy.WeatherProxy;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mintos.proxy.GeoProxy.getIp2GeoServiceNotAvailableErrMsg;
import static org.mintos.proxy.WeatherProxy.getWeatherServiceNotAvailableErrMsg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MintosServletTestBack {

    private static final String IP_ADDRESS = "1.1.1.1";
    @InjectMocks
    private MintosServlet mintosServlet;
    @Spy
    private GeoProxy geoProxy;
    @Spy
    private WeatherProxy weatherProxy;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;

    private ObjectMapper objectMapper = new ObjectMapper();
    private StubServletOutputStream servletOutputStream = new StubServletOutputStream();
    private GeoResponse geoResponse;
    private WeatherResponse weatherResponse;

    private InputStream ipResponseInputStream = this.getClass().getClassLoader().getResourceAsStream("ip_response.json");
    private InputStream weatherResponseInputStream = this.getClass().getClassLoader().getResourceAsStream("weather_response.json");

    {
        try {
            geoResponse = objectMapper.readValue(ipResponseInputStream, GeoResponse.class);
            weatherResponse = objectMapper.readValue(weatherResponseInputStream, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @BeforeEach
    void setUp() {
        doReturn(CompletableFuture.completedFuture(geoResponse)).when(geoProxy).getGeoResponse(anyString());
        doReturn(CompletableFuture.completedFuture(weatherResponse)).when(weatherProxy).getWeatherResponse(any(GeoResponse.class));
    }

    @Test
    void shouldPrintMockGeoResponse() {
        System.out.println(geoResponse);
    }

    @Test
    void shouldPrintMockWeatherResponse() {
        System.out.println(weatherResponse);
    }

    @Test
    void shouldReturnWeatherResponse() throws IOException {
        //Given
        geoProxy.setFailureRate(0d);
        weatherProxy.setFailureRate(0d);
        doReturn(IP_ADDRESS).when(httpServletRequest).getHeader(MintosServlet.getIpAddressHeaderKey());
        doReturn(servletOutputStream).when(httpServletResponse).getOutputStream();
        //When
        mintosServlet.doGet(httpServletRequest, httpServletResponse);
        //Then
        verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void shouldReturnErrorWhenIpHeaderIsEmpty() throws IOException {
        //Given
        geoProxy.setFailureRate(0d);
        weatherProxy.setFailureRate(0d);
        doReturn(servletOutputStream).when(httpServletResponse).getOutputStream();
        //When
        Assertions.assertThrows(CompletionException.class, () -> mintosServlet.doGet(httpServletRequest, httpServletResponse));
        //Then
        verify(httpServletResponse).setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
    }

    @Test
    void shouldReturnErrorWhenIp2GeoServiceUnavailable() throws IOException {
        //Given
        geoProxy.setFailureRate(1d);
        weatherProxy.setFailureRate(0d);
        doReturn(IP_ADDRESS).when(httpServletRequest).getHeader(MintosServlet.getIpAddressHeaderKey());
        doReturn(servletOutputStream).when(httpServletResponse).getOutputStream();
        //When
        Assertions.assertThrows(CompletionException.class, () -> mintosServlet.doGet(httpServletRequest, httpServletResponse));
        //Then
        verify(httpServletResponse).setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        assertTrue(servletOutputStream.baos.toString().contains(getIp2GeoServiceNotAvailableErrMsg()));
    }

    @Test
    void shouldReturnErrorWhenWeatherServiceUnavailable() throws IOException {
        //Given
        geoProxy.setFailureRate(0d);
        weatherProxy.setFailureRate(1d);
        doReturn(IP_ADDRESS).when(httpServletRequest).getHeader(MintosServlet.getIpAddressHeaderKey());
        doReturn(servletOutputStream).when(httpServletResponse).getOutputStream();
        //When
        Assertions.assertThrows(CompletionException.class, () -> mintosServlet.doGet(httpServletRequest, httpServletResponse));
        //Then
        verify(httpServletResponse).setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        assertTrue(servletOutputStream.baos.toString().contains(getWeatherServiceNotAvailableErrMsg()));
    }

    private class StubServletOutputStream extends ServletOutputStream {

        private ByteArrayOutputStream baos = new ByteArrayOutputStream();

        @Override
        public void write(int i) throws IOException {
            baos.write(i);
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }
}