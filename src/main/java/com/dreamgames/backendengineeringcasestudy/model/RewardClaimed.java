package com.dreamgames.backendengineeringcasestudy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RewardClaimed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long playerId;
    private boolean isClaimed;

    public RewardClaimed() {
    }

    public RewardClaimed(Long playerId) {
        this.playerId = playerId;
        // set default true for user can enter tournament by default first time
        this.isClaimed = true;
    }

    public RewardClaimed(Long playerId, boolean isClaimed) {
        this.playerId = playerId;
        this.isClaimed = isClaimed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public void setClaimed(boolean isClaimed) {
        this.isClaimed = isClaimed;
    }
}
