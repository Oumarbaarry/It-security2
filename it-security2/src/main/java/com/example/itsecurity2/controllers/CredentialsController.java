package com.example.itsecurity2.controllers;

import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.repositories.CredentialsRepository;
import com.example.itsecurity2.request.CredentialsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/credentials")
public class CredentialsController {

    private final CredentialsRepository repo;

    final private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/all")
    public ResponseEntity<List<Credentials>> getAllCredentials(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/test")
    public String test(){
        return repo.findByUsername("user1").toString();
    }

    @PostMapping("/login")
    public ResponseEntity<Credentials> getCredential(@RequestBody CredentialsRequest credentials){
        Credentials cred = repo.findByUsername(credentials.getUsername());
        if (cred != null){
            if(passwordEncoder.matches(credentials.getPassword(), cred.getPassword())){
                return ResponseEntity.ok(cred);
            } else {
                return ResponseEntity.ok(Credentials.builder()
                            .username("wrong password")
                        .build());
            }
        } else {
            return ResponseEntity.ok(Credentials.builder()
                        .username("wrong username")
                    .build());
        }
    }

//    @GetMapping("/login/{username}/{password}")
//    public ResponseEntity<Credentials> getCredentials(@PathVariable String username,
//                                                      @PathVariable String password){
//        Credentials cred = repo.findByUsername(username);
//        if (cred != null){
//            if(passwordEncoder.matches(password, cred.getPassword())){
//                return ResponseEntity.ok(cred);
//            } else {
//                return ResponseEntity.ok(Credentials.builder()
//                            .username("wrong password")
//                        .build());
//            }
//        } else {
//            return ResponseEntity.ok(Credentials.builder()
//                        .username("wrong username")
//                    .build());
//        }
//    }

    @PostMapping("/login/{username}/{password}")
    public String getCredentials(@PathVariable String username,
                                 @PathVariable String password){
        Credentials cred = repo.findByUsername(username);
        if (cred != null){
            if(passwordEncoder.matches(password, cred.getPassword())){
                return "Det gick att logga in";
            } else {
                return "Fel lösenord";
            }
        } else {
            return "Fel användarnamn";
        }
    }
}
