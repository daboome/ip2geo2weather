package org.mintos.model.weather;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.mintos.model.IdentifiableEntity;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "location",
    "current"
})
@Embeddable
public class WeatherResponse extends IdentifiableEntity {
    @JsonProperty("location")
    @Transient
    private Location location;
    @JsonProperty("current")
    @Embedded
    private Current current;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherResponse() {
    }

    /**
     * 
     * @param current
     * @param location
     */
    public WeatherResponse(Location location, Current current) {
        super();
        this.location = location;
        this.current = current;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public WeatherResponse withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("current")
    public Current getCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(Current current) {
        this.current = current;
    }

    public WeatherResponse withCurrent(Current current) {
        this.current = current;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public WeatherResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("location", location).append("current", current).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(location).append(current).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WeatherResponse) == false) {
            return false;
        }
        WeatherResponse rhs = ((WeatherResponse) other);
        return new EqualsBuilder().append(location, rhs.location).append(current, rhs.current).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
