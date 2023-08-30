package com.example.itsecurity2.bruteforce;

import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.repositories.CredentialsRepository;
import com.example.itsecurity2.request.CredentialsRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Bruteforce {

    private final RestTemplate restTemplate = new RestTemplate();
    //private final CredentialsRepository repo;

    public Credentials brutalForce(){

        // Define the request URL
        String url = "http://localhost:8080/login";

        // Create a request object
        CredentialsRequest request = new CredentialsRequest();
        request.setUsername("user1");
        request.setPassword("pass");

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create a request entity with the request body and headers
        HttpEntity<CredentialsRequest> requestEntity = new HttpEntity<>(request, headers);

        // Send the POST request
        ResponseEntity<Credentials> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Credentials.class
        );

        // Handle the response
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Credentials credentials = responseEntity.getBody();
            System.out.println("Response: " + credentials);
        } else {
            System.out.println("HTTP Request Failed with Status Code: " + responseEntity.getStatusCodeValue());
        }

//        boolean go = true;
//        while (go){
//
//            for (char letter = 'a'; letter <= 'z'; letter++) {
//
//            }
//
//        }

        return new Credentials();

    }
}