
package pl.partabelo.demo.Models.CurrentTruckModels;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timestamp",
    "context",
    "progress_ms",
    "item",
    "currently_playing_type",
    "actions",
    "is_playing"
})
@Generated("jsonschema2pojo")
public class CurrentTrack {

    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("context")
    private Context context;
    @JsonProperty("progress_ms")
    private Integer progressMs;
    @JsonProperty("item")
    private Item item;
    @JsonProperty("currently_playing_type")
    private String currentlyPlayingType;
    @JsonProperty("actions")
    private Actions actions;
    @JsonProperty("is_playing")
    private Boolean isPlaying;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public CurrentTrack withTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @JsonProperty("context")
    public Context getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(Context context) {
        this.context = context;
    }

    public CurrentTrack withContext(Context context) {
        this.context = context;
        return this;
    }

    @JsonProperty("progress_ms")
    public Integer getProgressMs() {
        return progressMs;
    }

    @JsonProperty("progress_ms")
    public void setProgressMs(Integer progressMs) {
        this.progressMs = progressMs;
    }

    public CurrentTrack withProgressMs(Integer progressMs) {
        this.progressMs = progressMs;
        return this;
    }

    @JsonProperty("item")
    public Item getItem() {
        return item;
    }

    @JsonProperty("item")
    public void setItem(Item item) {
        this.item = item;
    }

    public CurrentTrack withItem(Item item) {
        this.item = item;
        return this;
    }

    @JsonProperty("currently_playing_type")
    public String getCurrentlyPlayingType() {
        return currentlyPlayingType;
    }

    @JsonProperty("currently_playing_type")
    public void setCurrentlyPlayingType(String currentlyPlayingType) {
        this.currentlyPlayingType = currentlyPlayingType;
    }

    public CurrentTrack withCurrentlyPlayingType(String currentlyPlayingType) {
        this.currentlyPlayingType = currentlyPlayingType;
        return this;
    }

    @JsonProperty("actions")
    public Actions getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public CurrentTrack withActions(Actions actions) {
        this.actions = actions;
        return this;
    }

    @JsonProperty("is_playing")
    public Boolean getIsPlaying() {
        return isPlaying;
    }

    @JsonProperty("is_playing")
    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public CurrentTrack withIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
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

    public CurrentTrack withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
