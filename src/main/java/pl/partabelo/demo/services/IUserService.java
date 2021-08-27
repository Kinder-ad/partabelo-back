package pl.partabelo.demo.services;

import pl.partabelo.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User saveUser(User user);

    List<User> getUsers();

    void deleteUserByEmail(String email);

    void deleteUserById(Long id);

    void makeAdmin(String email);

    void makeMod(String email);

    void makeUser(String email);

    Optional<User> findByEmail(String email);
}
