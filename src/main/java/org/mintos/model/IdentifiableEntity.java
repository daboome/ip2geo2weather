package org.mintos.model;

import org.mintos.model.geo.GeoResponse;
import org.mintos.model.weather.WeatherResponse;

import javax.persistence.*;

@Entity
public class IdentifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column(name = "ip_address")
    private String ipAddress;
    @Embedded
    private GeoResponse geoResponse;
    @Embedded
    private WeatherResponse weatherResponse;

    public IdentifiableEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public IdentifiableEntity(GeoResponse geoResponse) {
        this.geoResponse = geoResponse;
    }

    public IdentifiableEntity(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public GeoResponse getGeoResponse() {
        return geoResponse;
    }

    public void setGeoResponse(GeoResponse geoResponse) {
        this.geoResponse = geoResponse;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}
