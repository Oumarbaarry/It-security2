package com.example.itsecurity2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BrutalForce {

    public static void main(String[] args) throws IOException {
        URL users = new URL("http://localhost:8080/credentials/all");
        BufferedReader in1 = new BufferedReader(new InputStreamReader(users.openStream()));
        String inlines = "";
        while((inlines = in1.readLine()) != null){
            System.out.println(inlines);
        }
        in1.close();
    }
}
