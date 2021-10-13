package pl.partabelo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.security.UserPrincipal;
import pl.partabelo.demo.services.IUserService;
import pl.partabelo.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> updateUser(@RequestBody User user, Principal principal){
        //(security) MOD cant change ADMIN role
        if (principal.getName().equals("mod")
                && !this.userService.findByEmail(user.getEmail()).get().getRole().toString().equalsIgnoreCase(user.getRole().toString())
        ){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


        this.userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        this.userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/request")
    public ResponseEntity<?> setRequest(@RequestBody User user){
        System.out.println(user);
        this.userService.setUserRequest(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
