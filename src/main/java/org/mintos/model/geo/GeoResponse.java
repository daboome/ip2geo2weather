package org.mintos.model.geo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mintos.model.IdentifiableEntity;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ip_address",
    "country",
    "country_code",
    "continent",
    "continent_code",
    "city",
    "county",
    "region",
    "region_code",
    "timezone",
    "owner",
    "longitude",
    "latitude",
    "currency",
    "languages",
    "warning"
})
@Embeddable
public class GeoResponse extends IdentifiableEntity {
    @JsonProperty("ip_address")
    @Transient
    private String ipAddress;
    @JsonProperty("country")
    @Column(name = "country")
    private String country;
    @JsonProperty("country_code")
    @Transient
    private String countryCode;
    @JsonProperty("continent")
    @Transient
    private String continent;
    @JsonProperty("continent_code")
    @Transient
    private String continentCode;
    @JsonProperty("city")
    @Column(name = "city")
    private String city;
    @JsonProperty("county")
    @Transient
    private String county;
    @JsonProperty("region")
    @Transient
    private String region;
    @JsonProperty("region_code")
    @Transient
    private String regionCode;
    @JsonProperty("timezone")
    @Transient
    private String timezone;
    @JsonProperty("owner")
    @Transient
    private Object owner;
    @JsonProperty("longitude")
    @Column(name = "longitude")
    private Double longitude;
    @JsonProperty("latitude")
    @Column(name = "latitude")
    private Double latitude;
    @JsonProperty("currency")
    @Transient
    private String currency;
    @JsonProperty("languages")
    @Transient
    private List<String> languages = null;
    @JsonProperty("warning")
    @Transient
    private String warning;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeoResponse() {
    }

    /**
     * 
     * @param continent
     * @param owner
     * @param country
     * @param languages
     * @param city
     * @param timezone
     * @param latitude
     * @param ipAddress
     * @param county
     * @param regionCode
     * @param countryCode
     * @param warning
     * @param currency
     * @param region
     * @param continentCode
     * @param longitude
     */
    public GeoResponse(String ipAddress, String country, String countryCode, String continent, String continentCode, String city, String county, String region, String regionCode, String timezone, Object owner, Double longitude, Double latitude, String currency, List<String> languages, String warning) {
        super();
        this.ipAddress = ipAddress;
        this.country = country;
        this.countryCode = countryCode;
        this.continent = continent;
        this.continentCode = continentCode;
        this.city = city;
        this.county = county;
        this.region = region;
        this.regionCode = regionCode;
        this.timezone = timezone;
        this.owner = owner;
        this.longitude = longitude;
        this.latitude = latitude;
        this.currency = currency;
        this.languages = languages;
        this.warning = warning;
    }

    @JsonProperty("ip_address")
    public String getIpAddress() {
        return ipAddress;
    }

    @JsonProperty("ip_address")
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("continent")
    public String getContinent() {
        return continent;
    }

    @JsonProperty("continent")
    public void setContinent(String continent) {
        this.continent = continent;
    }

    @JsonProperty("continent_code")
    public String getContinentCode() {
        return continentCode;
    }

    @JsonProperty("continent_code")
    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("county")
    public String getCounty() {
        return county;
    }

    @JsonProperty("county")
    public void setCounty(String county) {
        this.county = county;
    }

    @JsonProperty("region")
    public String getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(String region) {
        this.region = region;
    }

    @JsonProperty("region_code")
    public String getRegionCode() {
        return regionCode;
    }

    @JsonProperty("region_code")
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @JsonProperty("owner")
    public Object getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("languages")
    public List<String> getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @JsonProperty("warning")
    public String getWarning() {
        return warning;
    }

    @JsonProperty("warning")
    public void setWarning(String warning) {
        this.warning = warning;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("ipAddress", ipAddress).append("country", country).append("countryCode", countryCode).append("continent", continent).append("continentCode", continentCode).append("city", city).append("county", county).append("region", region).append("regionCode", regionCode).append("timezone", timezone).append("owner", owner).append("longitude", longitude).append("latitude", latitude).append("currency", currency).append("languages", languages).append("warning", warning).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(continent).append(owner).append(country).append(languages).append(city).append(timezone).append(latitude).append(ipAddress).append(county).append(regionCode).append(countryCode).append(warning).append(currency).append(additionalProperties).append(region).append(continentCode).append(longitude).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GeoResponse) == false) {
            return false;
        }
        GeoResponse rhs = ((GeoResponse) other);
        return new EqualsBuilder().append(continent, rhs.continent).append(owner, rhs.owner).append(country, rhs.country).append(languages, rhs.languages).append(city, rhs.city).append(timezone, rhs.timezone).append(latitude, rhs.latitude).append(ipAddress, rhs.ipAddress).append(county, rhs.county).append(regionCode, rhs.regionCode).append(countryCode, rhs.countryCode).append(warning, rhs.warning).append(currency, rhs.currency).append(additionalProperties, rhs.additionalProperties).append(region, rhs.region).append(continentCode, rhs.continentCode).append(longitude, rhs.longitude).isEquals();
    }

}
