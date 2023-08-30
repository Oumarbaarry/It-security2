package com.example.itsecurity2.scripts;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BrutalForce {

    private boolean request(String name, String password){
        try {
            HttpURLConnection request = (HttpURLConnection) new URL("http://localhost:8080/credentials/login").openConnection();
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            request.setDoOutput(true);

            String credentials = "username=" + name + "&password=" + password;

            try(OutputStream stream = request.getOutputStream()) {
                byte[] output = credentials.getBytes(StandardCharsets.UTF_8);
                stream.write(output, 0, output.length);
            }
            return request.getResponseCode() == 200;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(new BrutalForce().request("user1", "pass"));
    }
}
