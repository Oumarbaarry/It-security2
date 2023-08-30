package com.example.itsecurity2;

import com.example.itsecurity2.models.Credentials;
import com.example.itsecurity2.request.CredentialsRequest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Exchanger;

public class BrutalForce {

    public static void main(String[] args) throws IOException {

//        String password = "pass";
//        String urlString = "http://localhost:8080/credentials/login/user1/" + password;
//
//        URL users = new URL(urlString);
//        BufferedReader in1 = new BufferedReader(new InputStreamReader(users.openStream()));
//        String inlines = "";
//
//        while((inlines = in1.readLine()) != null){
//            System.out.println(inlines);
//        }
//        in1.close();
//
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
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Credentials> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                Credentials.class
        );

         //Handle the response
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            Credentials credentials = responseEntity.getBody();
            System.out.println("Response: " + credentials);
        } else {
            System.out.println("HTTP Request Failed with Status Code: " + responseEntity.getBody());
        }

//        try {
//            HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8080/credentials/login").openConnection();
//            con.setRequestMethod("POST");
//            con.setRequestProperty("Content-Type", "application/json; charset=utf8");
//            con.setDoOutput(true);
//
//            String formData = "username=user1, password=pass";
//
//            try(OutputStream os = con.getOutputStream()) {
//                byte[] input = formData.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//                System.out.println(con.getResponseCode());
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
