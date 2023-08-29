package com.example.itsecurity2;

import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.repositories.CredentialsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItSecurity2Application {

    public static void main(String[] args) {
        SpringApplication.run(ItSecurity2Application.class, args);
    }

    @Bean
    public CommandLineRunner init(CredentialsRepository repo){
        return (args) -> {
            repo.save(Credentials.builder()
                        .username("user1")
                        .password("pass1")
                    .build());

            repo.save(Credentials.builder()
                        .username("user2")
                        .password("pass2")
                    .build());
        };
    }
}
