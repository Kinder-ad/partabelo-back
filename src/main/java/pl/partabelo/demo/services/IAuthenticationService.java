package pl.partabelo.demo.services;

import pl.partabelo.demo.model.User;

public interface IAuthenticationService {


    User signInAndReturnJWT(User signInRequest);
}
