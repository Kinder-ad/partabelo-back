package pl.partabelo.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.*;

import java.security.Principal;

@Controller
@RestController
@RequestMapping("api/spotify")
public class SongController {

    final
    CurrentTrackService currentTrackService;

    final
    PlaylistService playlistService;



    final
    QueueService queueService;

    final
    SkipService skipService;


    public SongController(CurrentTrackService currentTrackService, PlaylistService playlistService, QueueService queueService,   SkipService skipService) {
        this.currentTrackService = currentTrackService;
        this.playlistService = playlistService;
        this.queueService = queueService;
        this.skipService = skipService;
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

    @PostMapping("/song/skipVote")
    public  ResponseEntity<?> addSkipVote(){
        System.out.println("asfasrfs");
        var isAllow = this.skipService.addUserVote();
        System.out.println(isAllow);
        if(isAllow) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/song/skipVotes")
    public ResponseEntity<?> getSkipVotes(){
        return new ResponseEntity<>(this.skipService.getVoteCounter(),HttpStatus.OK);
    }

    @GetMapping("/song/limitOfVotes")
    public ResponseEntity<?> getLimitOfVotes(){
        return new ResponseEntity<>(this.skipService.getLimitOfVotes(),HttpStatus.OK);
    }

    @PostMapping("/song")
    public ResponseEntity<?> addSongToSpotifyQueue(@RequestBody TrackInQueue trackJson) {

        if (this.queueService.checkIfNotInLocalQueueAndInLimit(trackJson.getTrackJson())) {
            this.queueService.addToLocalQueue(trackJson);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
