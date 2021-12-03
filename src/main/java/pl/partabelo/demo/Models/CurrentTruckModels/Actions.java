
package pl.partabelo.demo.Models.CurrentTruckModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "disallows"
})
@Generated("jsonschema2pojo")
public class Actions {

    @JsonProperty("disallows")
    private Disallows disallows;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("disallows")
    public Disallows getDisallows() {
        return disallows;
    }

    @JsonProperty("disallows")
    public void setDisallows(Disallows disallows) {
        this.disallows = disallows;
    }

    public Actions withDisallows(Disallows disallows) {
        this.disallows = disallows;
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

    public Actions withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
