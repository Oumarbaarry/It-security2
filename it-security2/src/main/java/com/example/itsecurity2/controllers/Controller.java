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
@RequestMapping("/credentials")
public class Controller {

    private final CredentialsRepository repo;

    final private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/all")
    public ResponseEntity<List<Credentials>> getAllCredentials(){
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/login")
    public ResponseEntity<Credentials> getCredentials(@RequestBody CredentialsRequest credentials){
        Credentials cred = repo.findByUserName(credentials.getUserName());
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
}
