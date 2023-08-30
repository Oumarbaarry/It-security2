package com.example.itsecurity2.controllers;

import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.repositories.CredentialsRepository;
import com.example.itsecurity2.request.CredentialsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credentials")
public class CredentialsController {

    private final CredentialsRepository repo;

    final private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<Credentials> loginForce(CredentialsRequest credentials){
        Credentials cred = repo.findByUsername(credentials.getUsername());
        if (cred != null){
            if(passwordEncoder.matches(credentials.getPassword(), cred.getPassword())){
                return new ResponseEntity<>(cred, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new Credentials(), HttpStatus.UNAUTHORIZED);
    }
}
