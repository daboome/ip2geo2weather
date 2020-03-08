package org.mintos.model.weather;

import java.util.HashMap;
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

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "last_updated_epoch",
        "last_updated",
        "temp_c",
        "temp_f",
        "is_day",
        "condition",
        "wind_mph",
        "wind_kph",
        "wind_degree",
        "wind_dir",
        "pressure_mb",
        "pressure_in",
        "precip_mm",
        "precip_in",
        "humidity",
        "cloud",
        "feelslike_c",
        "feelslike_f",
        "vis_km",
        "vis_miles",
        "uv",
        "gust_mph",
        "gust_kph"
})
@Embeddable
public class Current {
    @JsonProperty("last_updated_epoch")
    @Transient
    private Integer lastUpdatedEpoch;
    @JsonProperty("last_updated")
    @Transient
    private String lastUpdated;
    @JsonProperty("temp_c")
    @Column(name = "temp_C")
    private Double tempC;
    @JsonProperty("temp_f")
    @Column(name = "temp_F")
    private Double tempF;
    @JsonProperty("is_day")
    @Transient
    private Integer isDay;
    @JsonProperty("condition")
    @Transient
    private Condition condition;
    @JsonProperty("wind_mph")
    @Transient
    private Double windMph;
    @JsonProperty("wind_kph")
    @Transient
    private Double windKph;
    @JsonProperty("wind_degree")
    @Transient
    private Integer windDegree;
    @JsonProperty("wind_dir")
    @Transient
    private String windDir;
    @JsonProperty("pressure_mb")
    @Transient
    private Double pressureMb;
    @JsonProperty("pressure_in")
    @Transient
    private Double pressureIn;
    @JsonProperty("precip_mm")
    @Transient
    private Double precipMm;
    @JsonProperty("precip_in")
    @Transient
    private Double precipIn;
    @Transient
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("cloud")
    @Transient
    private Integer cloud;
    @JsonProperty("feelslike_c")
    @Transient
    private Double feelslikeC;
    @JsonProperty("feelslike_f")
    @Transient
    private Double feelslikeF;
    @JsonProperty("vis_km")
    @Transient
    private Double visKm;
    @JsonProperty("vis_miles")
    @Transient
    private Double visMiles;
    @JsonProperty("uv")
    @Transient
    private Double uv;
    @JsonProperty("gust_mph")
    @Transient
    private Double gustMph;
    @JsonProperty("gust_kph")
    @Transient
    private Double gustKph;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Current() {
    }

    /**
     * @param tempF
     * @param precipMm
     * @param uv
     * @param feelslikeC
     * @param gustMph
     * @param gustKph
     * @param windDir
     * @param pressureIn
     * @param precipIn
     * @param isDay
     * @param cloud
     * @param lastUpdated
     * @param condition
     * @param windMph
     * @param visKm
     * @param windKph
     * @param humidity
     * @param feelslikeF
     * @param windDegree
     * @param visMiles
     * @param pressureMb
     * @param lastUpdatedEpoch
     * @param tempC
     */
    public Current(Integer lastUpdatedEpoch, String lastUpdated, Double tempC, Double tempF, Integer isDay, Condition condition, Double windMph, Double windKph, Integer windDegree, String windDir, Double pressureMb, Double pressureIn, Double precipMm, Double precipIn, Integer humidity, Integer cloud, Double feelslikeC, Double feelslikeF, Double visKm, Double visMiles, Double uv, Double gustMph, Double gustKph) {
        super();
        this.lastUpdatedEpoch = lastUpdatedEpoch;
        this.lastUpdated = lastUpdated;
        this.tempC = tempC;
        this.tempF = tempF;
        this.isDay = isDay;
        this.condition = condition;
        this.windMph = windMph;
        this.windKph = windKph;
        this.windDegree = windDegree;
        this.windDir = windDir;
        this.pressureMb = pressureMb;
        this.pressureIn = pressureIn;
        this.precipMm = precipMm;
        this.precipIn = precipIn;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelslikeC = feelslikeC;
        this.feelslikeF = feelslikeF;
        this.visKm = visKm;
        this.visMiles = visMiles;
        this.uv = uv;
        this.gustMph = gustMph;
        this.gustKph = gustKph;
    }

    @JsonProperty("last_updated_epoch")
    public Integer getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    @JsonProperty("last_updated_epoch")
    public void setLastUpdatedEpoch(Integer lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public Current withLastUpdatedEpoch(Integer lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
        return this;
    }

    @JsonProperty("last_updated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("last_updated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Current withLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    @JsonProperty("temp_c")
    public Double getTempC() {
        return tempC;
    }

    @JsonProperty("temp_c")
    public void setTempC(Double tempC) {
        this.tempC = tempC;
    }

    public Current withTempC(Double tempC) {
        this.tempC = tempC;
        return this;
    }

    @JsonProperty("temp_f")
    public Double getTempF() {
        return tempF;
    }

    @JsonProperty("temp_f")
    public void setTempF(Double tempF) {
        this.tempF = tempF;
    }

    public Current withTempF(Double tempF) {
        this.tempF = tempF;
        return this;
    }

    @JsonProperty("is_day")
    public Integer getIsDay() {
        return isDay;
    }

    @JsonProperty("is_day")
    public void setIsDay(Integer isDay) {
        this.isDay = isDay;
    }

    public Current withIsDay(Integer isDay) {
        this.isDay = isDay;
        return this;
    }

    @JsonProperty("condition")
    public Condition getCondition() {
        return condition;
    }

    @JsonProperty("condition")
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Current withCondition(Condition condition) {
        this.condition = condition;
        return this;
    }

    @JsonProperty("wind_mph")
    public Double getWindMph() {
        return windMph;
    }

    @JsonProperty("wind_mph")
    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }

    public Current withWindMph(Double windMph) {
        this.windMph = windMph;
        return this;
    }

    @JsonProperty("wind_kph")
    public Double getWindKph() {
        return windKph;
    }

    @JsonProperty("wind_kph")
    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public Current withWindKph(Double windKph) {
        this.windKph = windKph;
        return this;
    }

    @JsonProperty("wind_degree")
    public Integer getWindDegree() {
        return windDegree;
    }

    @JsonProperty("wind_degree")
    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public Current withWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
        return this;
    }

    @JsonProperty("wind_dir")
    public String getWindDir() {
        return windDir;
    }

    @JsonProperty("wind_dir")
    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Current withWindDir(String windDir) {
        this.windDir = windDir;
        return this;
    }

    @JsonProperty("pressure_mb")
    public Double getPressureMb() {
        return pressureMb;
    }

    @JsonProperty("pressure_mb")
    public void setPressureMb(Double pressureMb) {
        this.pressureMb = pressureMb;
    }

    public Current withPressureMb(Double pressureMb) {
        this.pressureMb = pressureMb;
        return this;
    }

    @JsonProperty("pressure_in")
    public Double getPressureIn() {
        return pressureIn;
    }

    @JsonProperty("pressure_in")
    public void setPressureIn(Double pressureIn) {
        this.pressureIn = pressureIn;
    }

    public Current withPressureIn(Double pressureIn) {
        this.pressureIn = pressureIn;
        return this;
    }

    @JsonProperty("precip_mm")
    public Double getPrecipMm() {
        return precipMm;
    }

    @JsonProperty("precip_mm")
    public void setPrecipMm(Double precipMm) {
        this.precipMm = precipMm;
    }

    public Current withPrecipMm(Double precipMm) {
        this.precipMm = precipMm;
        return this;
    }

    @JsonProperty("precip_in")
    public Double getPrecipIn() {
        return precipIn;
    }

    @JsonProperty("precip_in")
    public void setPrecipIn(Double precipIn) {
        this.precipIn = precipIn;
    }

    public Current withPrecipIn(Double precipIn) {
        this.precipIn = precipIn;
        return this;
    }

    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Current withHumidity(Integer humidity) {
        this.humidity = humidity;
        return this;
    }

    @JsonProperty("cloud")
    public Integer getCloud() {
        return cloud;
    }

    @JsonProperty("cloud")
    public void setCloud(Integer cloud) {
        this.cloud = cloud;
    }

    public Current withCloud(Integer cloud) {
        this.cloud = cloud;
        return this;
    }

    @JsonProperty("feelslike_c")
    public Double getFeelslikeC() {
        return feelslikeC;
    }

    @JsonProperty("feelslike_c")
    public void setFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
    }

    public Current withFeelslikeC(Double feelslikeC) {
        this.feelslikeC = feelslikeC;
        return this;
    }

    @JsonProperty("feelslike_f")
    public Double getFeelslikeF() {
        return feelslikeF;
    }

    @JsonProperty("feelslike_f")
    public void setFeelslikeF(Double feelslikeF) {
        this.feelslikeF = feelslikeF;
    }

    public Current withFeelslikeF(Double feelslikeF) {
        this.feelslikeF = feelslikeF;
        return this;
    }

    @JsonProperty("vis_km")
    public Double getVisKm() {
        return visKm;
    }

    @JsonProperty("vis_km")
    public void setVisKm(Double visKm) {
        this.visKm = visKm;
    }

    public Current withVisKm(Double visKm) {
        this.visKm = visKm;
        return this;
    }

    @JsonProperty("vis_miles")
    public Double getVisMiles() {
        return visMiles;
    }

    @JsonProperty("vis_miles")
    public void setVisMiles(Double visMiles) {
        this.visMiles = visMiles;
    }

    public Current withVisMiles(Double visMiles) {
        this.visMiles = visMiles;
        return this;
    }

    @JsonProperty("uv")
    public Double getUv() {
        return uv;
    }

    @JsonProperty("uv")
    public void setUv(Double uv) {
        this.uv = uv;
    }

    public Current withUv(Double uv) {
        this.uv = uv;
        return this;
    }

    @JsonProperty("gust_mph")
    public Double getGustMph() {
        return gustMph;
    }

    @JsonProperty("gust_mph")
    public void setGustMph(Double gustMph) {
        this.gustMph = gustMph;
    }

    public Current withGustMph(Double gustMph) {
        this.gustMph = gustMph;
        return this;
    }

    @JsonProperty("gust_kph")
    public Double getGustKph() {
        return gustKph;
    }

    @JsonProperty("gust_kph")
    public void setGustKph(Double gustKph) {
        this.gustKph = gustKph;
    }

    public Current withGustKph(Double gustKph) {
        this.gustKph = gustKph;
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

    public Current withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("lastUpdatedEpoch", lastUpdatedEpoch).append("lastUpdated", lastUpdated).append("tempC", tempC).append("tempF", tempF).append("isDay", isDay).append("condition", condition).append("windMph", windMph).append("windKph", windKph).append("windDegree", windDegree).append("windDir", windDir).append("pressureMb", pressureMb).append("pressureIn", pressureIn).append("precipMm", precipMm).append("precipIn", precipIn).append("humidity", humidity).append("cloud", cloud).append("feelslikeC", feelslikeC).append("feelslikeF", feelslikeF).append("visKm", visKm).append("visMiles", visMiles).append("uv", uv).append("gustMph", gustMph).append("gustKph", gustKph).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(precipMm).append(feelslikeC).append(gustMph).append(gustKph).append(windDir).append(pressureIn).append(precipIn).append(cloud).append(lastUpdated).append(windMph).append(visKm).append(windKph).append(humidity).append(windDegree).append(visMiles).append(lastUpdatedEpoch).append(tempF).append(uv).append(isDay).append(condition).append(feelslikeF).append(pressureMb).append(additionalProperties).append(tempC).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Current) == false) {
            return false;
        }
        Current rhs = ((Current) other);
        return new EqualsBuilder().append(precipMm, rhs.precipMm).append(feelslikeC, rhs.feelslikeC).append(gustMph, rhs.gustMph).append(gustKph, rhs.gustKph).append(windDir, rhs.windDir).append(pressureIn, rhs.pressureIn).append(precipIn, rhs.precipIn).append(cloud, rhs.cloud).append(lastUpdated, rhs.lastUpdated).append(windMph, rhs.windMph).append(visKm, rhs.visKm).append(windKph, rhs.windKph).append(humidity, rhs.humidity).append(windDegree, rhs.windDegree).append(visMiles, rhs.visMiles).append(lastUpdatedEpoch, rhs.lastUpdatedEpoch).append(tempF, rhs.tempF).append(uv, rhs.uv).append(isDay, rhs.isDay).append(condition, rhs.condition).append(feelslikeF, rhs.feelslikeF).append(pressureMb, rhs.pressureMb).append(additionalProperties, rhs.additionalProperties).append(tempC, rhs.tempC).isEquals();
    }

}
