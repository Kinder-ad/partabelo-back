package pl.partabelo.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
