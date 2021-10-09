package pl.partabelo.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.partabelo.demo.model.Role;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.repository.IUserRepository;

import java.time.LocalDateTime;
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
        return this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        System.out.println(user.isPaid());
        this.userRepository.updateUser(user.getFirstName(),user.getLastName(),user.getEmail(),user.getRole(),user.isPaid(), user.getId());
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public void deleteUserByEmail(String email) {
        this.userRepository.deleteUserByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteUserById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }



}
