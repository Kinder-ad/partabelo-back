package pl.partabelo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.partabelo.demo.services.UserService;

@RestController
@RequestMapping("api/noFilter")
public class noFilterController {

    final private UserService userService;

    public noFilterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("stats/percentageOfPaid")
    public ResponseEntity<?> getPercentageOfPaids(){
        int numberOfpaid = 0;
        float percentage = 0;
        int total = this.userService.getUsers().size();
        for (int i = 0; i < this.userService.getUsers().size(); i++) {
            if(this.userService.getUsers().get(i).isPaid()){
                numberOfpaid+=1;
            }
            percentage =  (numberOfpaid*100)/total;
            System.out.println(numberOfpaid+" numberOfpaid");
            System.out.println((numberOfpaid*100)/total);
        }
        return new ResponseEntity<>(percentage, HttpStatus.OK);
    }
}
