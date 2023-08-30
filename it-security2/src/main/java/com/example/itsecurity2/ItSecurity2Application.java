package com.example.itsecurity2;

import com.example.itsecurity2.bruteforce.Bruteforce;
import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.repositories.CredentialsRepository;
import com.example.itsecurity2.request.CredentialsRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ItSecurity2Application {

    public static void main(String[] args) {
        SpringApplication.run(ItSecurity2Application.class, args);
    }

    @Bean
    public CommandLineRunner init(CredentialsRepository repo){
        return (args) -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            repo.save(Credentials.builder()
                        .username("user1")
                        .password(passwordEncoder.encode("pass"))
                    .build());

            repo.save(Credentials.builder()
                        .username("user2")
                        .password(passwordEncoder.encode("pass"))
                    .build());

//            Bruteforce b = new Bruteforce();
//            b.brutalForce();
        };
    }
}
