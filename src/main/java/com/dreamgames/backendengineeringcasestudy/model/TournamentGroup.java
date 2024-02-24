package com.dreamgames.backendengineeringcasestudy.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TournamentGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tournamentId;

    private Long turkishPlayer = Long.valueOf(0);
    private int turkishScore = 0;
    private Long usPlayer = Long.valueOf(0);
    private int usScore = 0;
    private Long ukPlayer = Long.valueOf(0);
    private int ukScore = 0;
    private Long frenchPlayer = Long.valueOf(0);
    private int frenchScore = 0;
    private Long germanPlayer = Long.valueOf(0);
    private int germanScore = 0;
    private LocalDateTime createdAt = LocalDateTime.now();

    public TournamentGroup() {
    }

    // constructor for saving tournament group after according to player country
    public TournamentGroup(Long tournamentId, int turkishPlayer, int usPlayer, int ukPlayer, int frenchPlayer,
            int germanPlayer) {
        this.tournamentId = tournamentId;
        this.turkishPlayer = Long.valueOf(turkishPlayer);
        this.usPlayer = Long.valueOf(usPlayer);
        this.ukPlayer = Long.valueOf(ukPlayer);
        this.frenchPlayer = Long.valueOf(frenchPlayer);
        this.germanPlayer = Long.valueOf(germanPlayer);
        this.turkishScore = 0;
        this.usScore = 0;
        this.ukScore = 0;
        this.frenchScore = 0;
        this.germanScore = 0;
    }

    public TournamentGroup(Long tournamentId,
            Long turkishPlayer, int turkishScore,
            Long usPlayer, int usScore,
            Long ukPlayer,
            int ukScore,
            Long frenchPlayer, int frenchScore,
            Long germanPlayer, int germanScore, LocalDateTime createdAt) {
        this.tournamentId = tournamentId;
        this.turkishPlayer = turkishPlayer;
        this.turkishScore = turkishScore;
        this.usPlayer = usPlayer;
        this.usScore = usScore;
        this.ukPlayer = ukPlayer;
        this.ukScore = ukScore;
        this.frenchPlayer = frenchPlayer;
        this.frenchScore = frenchScore;
        this.germanPlayer = germanPlayer;
        this.germanScore = germanScore;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getTurkishPlayer() {
        return turkishPlayer;
    }

    public void setTurkishPlayer(Long turkishPlayer) {
        this.turkishPlayer = turkishPlayer;
    }

    public int getTurkishScore() {
        return turkishScore;
    }

    public void setTurkishScore(int turkishScore) {
        this.turkishScore = turkishScore;
    }

    public Long getUsPlayer() {
        return usPlayer;
    }

    public void setUsPlayer(Long usPlayer) {
        this.usPlayer = usPlayer;
    }

    public int getUsScore() {
        return usScore;
    }

    public void setUsScore(int usScore) {
        this.usScore = usScore;
    }

    public Long getUkPlayer() {
        return ukPlayer;
    }

    public void setUkPlayer(Long ukPlayer) {
        this.ukPlayer = ukPlayer;
    }

    public int getUkScore() {
        return ukScore;
    }

    public void setUkScore(int ukScore) {
        this.ukScore = ukScore;
    }

    public Long getFrenchPlayer() {
        return frenchPlayer;
    }

    public void setFrenchPlayer(Long frenchPlayer) {
        this.frenchPlayer = frenchPlayer;
    }

    public int getFrenchScore() {
        return frenchScore;
    }

    public void setFrenchScore(int frenchScore) {
        this.frenchScore = frenchScore;
    }

    public Long getGermanPlayer() {
        return germanPlayer;
    }

    public void setGermanPlayer(Long germanPlayer) {
        this.germanPlayer = germanPlayer;
    }

    public int getGermanScore() {
        return germanScore;
    }

    public void setGermanScore(int germanScore) {
        this.germanScore = germanScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
