package pl.partabelo.demo;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.partabelo.demo.model.User;
import pl.partabelo.demo.services.IUserService;
import pl.partabelo.demo.services.UserService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableSwagger2
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class DemoApplication {




    public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }

    @PostConstruct
    public void run(){
    }






}
