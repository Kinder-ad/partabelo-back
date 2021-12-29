package pl.partabelo.demo.services;

import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.model.TrackHistory;
import pl.partabelo.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User saveUser(User user);

    void updateUser(User user);

    void setUserRequest(User user);

    List<User> getUsers();

    void deleteUserByUsername(String email);

    void deleteUserById(Long id);

    Optional<User> findByUsername(String username);

    void addTrackToUserHistory(TrackInQueue trackInQueue);

    List<TrackHistory> getTrackHistoryOfUser(Long userId);

    User getUser(Long userId);
}
