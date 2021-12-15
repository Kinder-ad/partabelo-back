package pl.partabelo.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.QueueService;
import org.springframework.stereotype.Controller;

@Controller
@RestController
@RequestMapping("api/spotify")
public class QueueController {

    final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/queue")
    public ResponseEntity<?> getQueue() {
        return new ResponseEntity<>(this.queueService.getLocalQueue(), HttpStatus.OK);
    }
    @DeleteMapping("/queue/{uri}")
    public ResponseEntity<?> deleteIdQueue(@PathVariable String uri){
        this.queueService.deleteTrackByUriFromQueue(uri);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
