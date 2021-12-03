package pl.partabelo.demo.repository;

import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlaylistRepository {

    private List<TrackJson> playlist = new ArrayList<>();

    public void addTrackJsonToList(TrackJson trackJson){
        this.playlist.add(trackJson);
    }

    public List<TrackJson> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<TrackJson> playlist) {
        this.playlist = playlist;
    }
}
