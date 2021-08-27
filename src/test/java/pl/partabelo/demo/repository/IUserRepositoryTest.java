package pl.partabelo.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.IUserService;
import pl.partabelo.demo.services.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserRepositoryTest {
    @Autowired
    private IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void saveStudent(){
        System.out.println(this.userService.getUsers());

        User student = User.builder()
                .email("lucy@onet.eu")
                .firstName("Lucy")
                .lastName("Fornalik")
                .password("123Lucy")
                .build();
            this.userService.saveUser(student);
        System.out.println(this.userService.getUsers());
    }
    @Test
    public void updateUser(){
        System.out.println(this.userService.getUsers());
        Optional<User> user = userService.findByEmail("schila@onet.eu");
        user.get().setFirstName("Schilula");
        this.userService.saveUser(user.get());
        System.out.println(user.get().getPassword());
        System.out.println(this.userService.getUsers());
    }
    @Test
    public void isPresentTest(){
        System.out.println(this.userService.findByEmail("schila@onet.eu").isPresent());

        System.out.println(this.userService.findByEmail("schila@onet.eu").isEmpty());

        System.out.println(this.userService.findByEmail("schilaa@onet.eu").isPresent());

        System.out.println(this.userService.findByEmail("schilaa@onet.eu").isEmpty());
    }


    @Test
    public void test(){
        userService.makeMod("schila@onet.eu");
        System.out.println(userService.findByEmail("schila@onet.eu"));
        userService.makeUser("schila@onet.eu");
        System.out.println(userService.findByEmail("schila@onet.eu"));
    }
    @Test
    public void deleteUseR(){
        userService.deleteUserByEmail("schila@onet.eu");
        System.out.println(userService.getUsers());
    }

    @Test
    public void getAllStudents(){
        System.out.println(userService.getUsers());
    }
}
