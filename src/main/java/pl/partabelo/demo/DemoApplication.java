package pl.partabelo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class DemoApplication {




    public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }





}
