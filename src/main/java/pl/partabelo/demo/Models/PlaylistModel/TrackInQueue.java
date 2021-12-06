package pl.partabelo.demo.Models.PlaylistModel;

import pl.partabelo.demo.model.User;

import java.io.Serializable;

public class TrackInQueue implements Serializable {
    private TrackJson trackJson;
    private User user;

    public TrackInQueue(TrackJson trackJson, User user) {
        this.trackJson = trackJson;
        this.user = new User(user.getFirstName(),user.getLastName(), user.getRole());
    }

    public TrackJson getTrackJson() {
        return trackJson;
    }

    public void setTrackJson(TrackJson trackJson) {
        this.trackJson = trackJson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TrackInQueue{" +
                "trackJson=" + trackJson +
                ", user=" + user +
                '}';
    }
}
