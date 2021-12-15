package pl.partabelo.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.IUserService;

import java.util.Optional;

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
                .username("lucy@onet.eu")
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
        Optional<User> user = userService.findByUsername("schila@onet.eu");
        user.get().setFirstName("Schilula");
        this.userService.saveUser(user.get());
        System.out.println(user.get().getPassword());
        System.out.println(this.userService.getUsers());
    }
    @Test
    public void isPresentTest(){
        System.out.println(this.userService.findByUsername("schila@onet.eu").isPresent());

        System.out.println(this.userService.findByUsername("schila@onet.eu").isEmpty());

        System.out.println(this.userService.findByUsername("schilaa@onet.eu").isPresent());

        System.out.println(this.userService.findByUsername("schilaa@onet.eu").isEmpty());
    }


    @Test
    public void test(){
    }
    @Test
    public void deleteUseR(){
        userService.deleteUserByUsername("schila@onet.eu");
        System.out.println(userService.getUsers());
    }

    @Test
    public void getAllStudents(){
        System.out.println(userService.getUsers());
    }
}
