package pl.partabelo.demo.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "track_history")
public class TrackHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trackName;
    private String trackUrl;
    private LocalDateTime addedDateTime;

    public TrackHistory(String trackName, String trackUrl) {
        this.trackName = trackName;
        this.trackUrl = trackUrl;
        this.addedDateTime = LocalDateTime.now();
    }

    public TrackHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public LocalDateTime getAddedDateTime() {
        return addedDateTime;
    }

    public void setAddedDateTime(LocalDateTime addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    @Override
    public String toString() {
        return "TrackHistory{" +
                "id=" + id +
                ", trackName='" + trackName + '\'' +
                ", trackUrl='" + trackUrl + '\'' +
                ", addedDateTime=" + addedDateTime +
                '}';
    }
}
