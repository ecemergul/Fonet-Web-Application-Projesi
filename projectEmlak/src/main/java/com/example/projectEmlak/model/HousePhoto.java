package com.example.projectEmlak.model;

import jakarta.persistence.Lob;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HousePhoto {
    private String fileName;

    private String contentType;

    @Lob
    private byte[] data;

    // Constructors, getters, and setters

    public HousePhoto() {
        // Load the static photo from the resources folder during entity creation
        this.fileName = "static/houseEntity.jpg"; // Change this to your static photo file name
        this.contentType = "image/jpeg";

        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            this.data = Files.readAllBytes(path);
        } catch (IOException | NullPointerException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }


}
