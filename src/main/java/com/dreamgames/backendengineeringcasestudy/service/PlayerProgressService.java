package com.dreamgames.backendengineeringcasestudy.service;

import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.PlayerProgress;
import com.dreamgames.backendengineeringcasestudy.repository.PlayerProgressRepository;

@Service
public class PlayerProgressService {

    private final PlayerProgressRepository playerProgressRepository;

    public PlayerProgressService(PlayerProgressRepository playerProgressRepository) {
        this.playerProgressRepository = playerProgressRepository;
    }

    public PlayerProgress addNewPlayerProgress(PlayerProgress playerProgress) {
        PlayerProgress createdPlayerProgress = playerProgressRepository.save(playerProgress);
        return createdPlayerProgress;
    }

    public PlayerProgress getByPlayerId(Long playerId) {
        return playerProgressRepository.findByPlayerId(playerId);
    }

    public void payTournamentEntryPrice(Long playerId) {
        playerProgressRepository.decreaseThousandCoin(playerId);
    }

}
