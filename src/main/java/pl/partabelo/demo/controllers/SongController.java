package pl.partabelo.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.model.Test;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.repository.ISongRepository;
import pl.partabelo.demo.services.*;

@Controller
@RestController
@RequestMapping("api/spotify")
public class SongController {

    final
    CurrentTrackService currentTrackService;

    final
    PlaylistService playlistService;

    final
    UserService userService;

    final
    QueueService queueService;

    final
    SkipService skipService;

    final ISongRepository iSongRepository;


    public SongController(CurrentTrackService currentTrackService, PlaylistService playlistService, UserService userService, QueueService queueService, SkipService skipService, ISongRepository iSongRepository) {
        this.currentTrackService = currentTrackService;
        this.playlistService = playlistService;
        this.userService = userService;
        this.queueService = queueService;
        this.skipService = skipService;
        this.iSongRepository = iSongRepository;
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
    public  ResponseEntity<?> addSkipVote(@RequestBody User user){
        var isAllow = this.skipService.addUserVote(user);
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
    @PutMapping("/song/changeLimit/{number}")
    public ResponseEntity<?> changeLimit(@PathVariable("number") Integer newLimit){
        this.skipService.clearVote();
        this.skipService.setLimitOfVotes(newLimit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/song")
    public ResponseEntity<?> addSongToSpotifyQueue(@RequestBody TrackInQueue trackJson) {
        System.out.println(trackJson.toString());
        if (this.queueService.checkIfNotInLocalQueueAndInLimit(trackJson.getTrackJson())) {
            this.queueService.addToLocalQueue(trackJson);

            this.userService.addTrackToUserHistory(trackJson);

            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/song/history")
    public ResponseEntity<?> getHistoryAddedSong() {
        Test track =  this.iSongRepository.getAll().get(0);
        System.out.println(track.getUserName());
        System.out.println(track.getTrackName());
        return new ResponseEntity<>(this.iSongRepository.findAll(),HttpStatus.OK);
    }

}
