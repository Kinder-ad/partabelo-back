
package pl.partabelo.demo.Models.CurrentTruckModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "resuming"
})
@Generated("jsonschema2pojo")
public class Disallows {

    @JsonProperty("resuming")
    private Boolean resuming;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("resuming")
    public Boolean getResuming() {
        return resuming;
    }

    @JsonProperty("resuming")
    public void setResuming(Boolean resuming) {
        this.resuming = resuming;
    }

    public Disallows withResuming(Boolean resuming) {
        this.resuming = resuming;
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

    public Disallows withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
