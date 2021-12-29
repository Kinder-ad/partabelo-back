package pl.partabelo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.IAuthenticationService;
import pl.partabelo.demo.services.IUserService;
import pl.partabelo.demo.services.SpotifyApiService;

@Controller
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final SpotifyApiService spotifyApiService;
    private final IAuthenticationService authenticationService;

    private final IUserService userService;

    public AuthenticationController(SpotifyApiService spotifyApiService, IAuthenticationService authenticationService, IUserService userService) {
        this.spotifyApiService = spotifyApiService;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user){
        if(userService.findByUsername(user.getUsername().toLowerCase()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        user.setUsername(user.getUsername().toLowerCase());
        User userToSend = authenticationService.signInAndReturnJWT(user);
        userToSend.setPassword(null);
        return new ResponseEntity<>(userToSend, HttpStatus.OK);
    }
    @GetMapping("/spotify")
    public ResponseEntity<?> getToken(){
        this.spotifyApiService.setJwtAuto();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
