
package pl.partabelo.demo.Models.DeviceModel;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "is_active",
    "is_private_session",
    "is_restricted",
    "name",
    "type",
    "volume_percent"
})
@Generated("jsonschema2pojo")
public class Device {

    @JsonProperty("id")
    private String id;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("is_private_session")
    private Boolean isPrivateSession;
    @JsonProperty("is_restricted")
    private Boolean isRestricted;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("volume_percent")
    private Integer volumePercent;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("is_active")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("is_active")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("is_private_session")
    public Boolean getIsPrivateSession() {
        return isPrivateSession;
    }

    @JsonProperty("is_private_session")
    public void setIsPrivateSession(Boolean isPrivateSession) {
        this.isPrivateSession = isPrivateSession;
    }

    @JsonProperty("is_restricted")
    public Boolean getIsRestricted() {
        return isRestricted;
    }

    @JsonProperty("is_restricted")
    public void setIsRestricted(Boolean isRestricted) {
        this.isRestricted = isRestricted;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("volume_percent")
    public Integer getVolumePercent() {
        return volumePercent;
    }

    @JsonProperty("volume_percent")
    public void setVolumePercent(Integer volumePercent) {
        this.volumePercent = volumePercent;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
