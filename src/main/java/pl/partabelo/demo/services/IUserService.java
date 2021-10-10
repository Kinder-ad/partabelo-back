package pl.partabelo.demo.services;

import pl.partabelo.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User saveUser(User user);

    void updateUser(User user);

    void setUserRequest(User user);

    List<User> getUsers();

    void deleteUserByEmail(String email);

    void deleteUserById(Long id);

    Optional<User> findByEmail(String email);
}
