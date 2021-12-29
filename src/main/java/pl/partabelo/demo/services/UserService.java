package pl.partabelo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.partabelo.demo.Models.PlaylistModel.TrackInQueue;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.TrackHistory;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.repository.IUserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setPaid(false);
        user.setCreateTime(LocalDateTime.now());
        user.setRequest(false);
        user.setUsername(user.getUsername().toLowerCase());
        return this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        System.out.println(user.isPaid());
        this.userRepository.updateUser(user.getFirstName(),user.getLastName(),user.getUsername(),user.getRole(),user.isPaid(), user.isRequest(), user.getId());
    }

    @Override
    public void addTrackToUserHistory(TrackInQueue trackInQueue) {
        TrackHistory trackHistory = new TrackHistory(trackInQueue.getTrackJson().getName(),trackInQueue.getTrackJson().getUrl());
        Optional<User> user = this.userRepository.findByUsername(trackInQueue.getUser().getUsername());
        if(user.isPresent()){
            user.get().addTrackToHistory(trackHistory);
            this.userRepository.save(user.get());
        }else{
            System.out.println("Błąd");
        }
    }

    @Override
    public void setUserRequest(User user) {
        this.userRepository.setRequestUser(user.isRequest(), user.getId());
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public void deleteUserByUsername(String username) {
        this.userRepository.deleteUserByUsername(username);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteUserById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<TrackHistory> getTrackHistoryOfUser(Long userId) {
        List<TrackHistory> tracks = this.userRepository.getById(userId).getTracks();
        return tracks;
    }

    @Override
    public User getUser(Long userId) {
        User user = this.userRepository.findById(userId).get();
        user.setId(null);
        user.setPassword(null);
        user.setToken(null);
        return user;
    }
}
