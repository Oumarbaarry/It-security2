package com.example.itsecurity2.bruteforce;

import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.repositories.CredentialsRepository;
import com.example.itsecurity2.request.CredentialsRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class Bruteforce {

    private final RestTemplate restTemplate;
    private final CredentialsRepository repo;

    public Credentials brutalForce(CredentialsRequest request){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<CredentialsRequest> requestEntity = new HttpEntity<>(request, headers);

        String url = "http://localhost:8080/login"; // Replace with your actual URL
        ResponseEntity<Credentials> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Credentials.class
        );

        boolean go = true;
        while (go){

            for (char letter = 'a'; letter <= 'z'; letter++) {

            }

        }

    }
}