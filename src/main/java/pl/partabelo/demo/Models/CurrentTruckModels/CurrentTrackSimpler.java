package pl.partabelo.demo.Models.CurrentTruckModels;

public class CurrentTrackSimpler {
    private String name;
    private Integer progressMs;
    private Integer durationMs;
    private Boolean isPlaying;
    private String imageUrl;
    private String artist;

    public CurrentTrackSimpler(String name, Integer progressMs, Integer durationMs, Boolean isPlaying, String imageUrl, String artist) {
        this.progressMs = progressMs;
        this.isPlaying = isPlaying;
        this.name = name;
        this.durationMs = durationMs;
        this.imageUrl = imageUrl;
        this.artist = artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getProgressMs() {
        return progressMs;
    }

    public void setProgressMs(Integer progressMs) {
        this.progressMs = progressMs;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "CurrentTrackToSendModel{" +
                "name='" + name + '\'' +
                ", progressMs=" + progressMs +
                ", durationMs=" + durationMs +
                ", isPlaying=" + isPlaying +
                ", imageUrl='" + imageUrl + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
