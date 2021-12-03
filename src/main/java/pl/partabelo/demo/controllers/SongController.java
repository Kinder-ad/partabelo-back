package pl.partabelo.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import pl.partabelo.demo.services.*;

@Controller
@RestController
@RequestMapping("/spotify")
public class SongController {

    final
    CurrentTrackService currentTrackService;

    final
    PlaylistService playlistService;

    final
    SpotifyApiService spotifyApiService;

    final
    QueueService queueService;


    public SongController(CurrentTrackService currentTrackService, PlaylistService playlistService, SpotifyApiService spotifyApiService, QueueService queueService) {
        this.currentTrackService = currentTrackService;
        this.playlistService = playlistService;
        this.spotifyApiService = spotifyApiService;
        this.queueService = queueService;
    }

    @GetMapping("/authorize")
    public void getToken(){
        this.spotifyApiService.setJwtAuto();


    }

    @GetMapping("/song/current")
    public ResponseEntity<?> getCurrentTrack() {
        return new ResponseEntity<>(this.currentTrackService.getCurrentTrack(), HttpStatus.OK);
    }

    @GetMapping("/song")
    public ResponseEntity<?> getSongs() {
        return new ResponseEntity<>(this.playlistService.getPlaylist(), HttpStatus.OK);
    }

    @GetMapping("/song/skip")
    public void skipSong() {
        this.currentTrackService.skipSong();
    }

    @PostMapping("/song")
    public ResponseEntity<?> addSongToSpotifyQueue(@RequestBody TrackJson trackJson) {
        System.out.println(this.queueService.checkIfNotInLocalQueueAndInLimit(trackJson));
        if (this.queueService.checkIfNotInLocalQueueAndInLimit(trackJson)) {
            this.queueService.addToLocalQueue(trackJson);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
