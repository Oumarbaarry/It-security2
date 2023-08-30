package com.example.itsecurity2.scripts;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BrutalForce {

    String api = "http://localhost:8080/credentials/login";

    private boolean request(String user, String password){
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(api).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);

            String formData = "username=" + user + "&password=" + password;

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = formData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            return con.getResponseCode() == 200;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(new BrutalForce().request("user1", "pass"));
    }
}
