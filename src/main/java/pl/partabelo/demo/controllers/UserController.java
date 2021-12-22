package pl.partabelo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> getUsers(){
        List<User> users = new ArrayList<User>();
        for (User user :
                userService.getUsers()) {
            user.setPassword(null);
            users.add(user);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> updateUser(@RequestBody User user, Principal principal){
        //(security) MOD cant change ADMIN role
        if (principal.getName().equals("mod")
                && !this.userService.findByUsername(user.getUsername()).get().getRole().toString().equalsIgnoreCase(user.getRole().toString())
        ){

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
