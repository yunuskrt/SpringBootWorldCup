package com.dreamgames.backendengineeringcasestudy.model;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String country;

    private static final String[] COUNTRIES = { "Turkey", "United States", "United Kingdom", "France", "Germany" };

    public Player() {
    }

    // constructor for saving player
    public Player(PlayerToAdd playerToAdd) {
        this.username = playerToAdd.getUsername();
        // set country randomly for the created player
        this.country = getRandomCountry();
    }

    public Player(String username, String country) {
        this.username = username;
        this.country = country;
    }

    private String getRandomCountry() {
        Random random = new Random();
        int index = random.nextInt(COUNTRIES.length);
        return COUNTRIES[index];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
