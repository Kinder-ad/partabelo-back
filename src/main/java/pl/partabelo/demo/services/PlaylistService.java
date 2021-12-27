package pl.partabelo.demo.services;

import pl.partabelo.demo.Models.PlaylistModel.Item;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import pl.partabelo.demo.Models.PlaylistModel.Tracks;
import pl.partabelo.demo.repository.PlaylistRepository;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SpotifyApiService spotifyApiService;
    private String playlistId = "4VXDNYeBdAHLY0AN4MSWQD";

    public PlaylistService(PlaylistRepository playlistRepository, SpotifyApiService spotifyApiService) {
        this.playlistRepository = playlistRepository;
        this.spotifyApiService = spotifyApiService;
    }
    public List<TrackJson> getPlaylist(){
        this.setPlaylist();
        return this.playlistRepository.getPlaylist();
    }

    public void setPlaylist() {
        if (spotifyApiService.getJwt() == null) return;
            for (int i = 0; i < 6; i++) {
                Tracks tracks = this.spotifyApiService.templator(
                        "https://api.spotify.com/v1/playlists/"+this.playlistId+"/tracks?market=ES&fields=items(track(name%2Curi%2Calbum(images(url)))))&limit=100&offset=" + i * 100,
                        HttpMethod.GET,
                        Tracks.class
                );
                for (Item item : tracks.getItems()) {
                    if (this.playlistRepository.getPlaylist().stream().noneMatch((o) -> o.getName().equals(item.getTrack().getName()))) {
                        this.playlistRepository.addTrackJsonToList(new TrackJson(
                                item.getTrack().getName(),
                                item.getTrack().getUri(),
                                item.getTrack().getDurationMs(),
                                item.getTrack().getAlbum().getImages().get(2).getUrl(),
                                item.getTrack().getArtists().get(0).getName()
                        ));
                    }
                }
            }
    }

}
