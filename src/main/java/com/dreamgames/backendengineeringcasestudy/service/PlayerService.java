package com.dreamgames.backendengineeringcasestudy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.Player;
import com.dreamgames.backendengineeringcasestudy.repository.PlayerRepository;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player addNewPlayer(Player player) {
        Player createdPlayer = playerRepository.save(player);
        return createdPlayer;
    }
}
