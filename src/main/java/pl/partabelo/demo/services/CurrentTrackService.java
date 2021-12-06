package pl.partabelo.demo.services;

import pl.partabelo.demo.Models.CurrentTruckModels.CurrentTrack;
import pl.partabelo.demo.Models.CurrentTruckModels.CurrentTrackSimpler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrentTrackService {
    @Autowired
    SpotifyApiService spotifyApiService;

    private CurrentTrackSimpler currentTrack;

    @Scheduled(fixedDelay = 3000)
    private void setCurrentTrack() {

        if (this.spotifyApiService.getJwt() == null) return;
        CurrentTrack a = spotifyApiService.templator("https://api.spotify.com/v1/me/player/currently-playing?market=ES", HttpMethod.GET, CurrentTrack.class);
        if(a==null) return;
        CurrentTrackSimpler currentTrack = new CurrentTrackSimpler(
                a.getItem().getName(),
                a.getProgressMs(),
                a.getItem().getDurationMs(),
                a.getIsPlaying(),
                a.getItem().getAlbum().getImages().get(0).getUrl(),
                a.getItem().getArtists().get(0).getName()
        );
        this.currentTrack = currentTrack;
    }
    public void skipSong(){
        this.spotifyApiService.templator("https://api.spotify.com/v1/me/player/next?device_id="+this.spotifyApiService.getCurrentDevice(), HttpMethod.POST,void.class);
    }


    public CurrentTrackSimpler getCurrentTrack() {
        return currentTrack;
    }

}
