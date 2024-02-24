package com.dreamgames.backendengineeringcasestudy.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dreamgames.backendengineeringcasestudy.model.Player;
import com.dreamgames.backendengineeringcasestudy.model.PlayerProgress;
import com.dreamgames.backendengineeringcasestudy.model.Tournament;
import com.dreamgames.backendengineeringcasestudy.service.PlayerProgressService;
import com.dreamgames.backendengineeringcasestudy.service.PlayerService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentStatusService;

@RestController
@RequestMapping("api/v1/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final PlayerService playerService;
    private final TournamentStatusService tournamentStatusService;
    private final PlayerProgressService playerProgressService;

    public TournamentController(TournamentService tournamentService, PlayerService playerService,
            TournamentStatusService tournamentStatusService, PlayerProgressService playerProgressService) {
        this.tournamentService = tournamentService;
        this.playerService = playerService;
        this.tournamentStatusService = tournamentStatusService;
        this.playerProgressService = playerProgressService;
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveTournament() {
        Optional<Tournament> activeTournament = tournamentService.getActiveTournament();
        if (activeTournament.isPresent()) {
            return ResponseEntity.status(200).body(activeTournament.get());
        }
        return ResponseEntity.status(404).body("No active tournament found");
    }

    @PostMapping()
    public ResponseEntity<?> enterTournament(@RequestParam("playerId") String playerId) {
        // check if playerId exist in search query
        if (playerId == null || playerId.isEmpty()) {
            return ResponseEntity.status(400).body("Player id is required");
        }
        try {
            // check if playerId is a number
            Long longPlayerId = Long.parseLong(playerId);
            Optional<Player> optionalPlayer = playerService.getPlayerById(longPlayerId);
            if (!optionalPlayer.isPresent()) {
                return ResponseEntity.status(404).body("Player with id " + playerId + " not found");
            }
            // check if player is level 20 and has 1000 coins
            Player player = optionalPlayer.get();
            PlayerProgress playerProgress = playerProgressService.getByPlayerId(player.getId());
            if (playerProgress.getLevel() < 20 || playerProgress.getCoin() < 1000) {
                return ResponseEntity.status(400).body("Player must be level 20 and have 1000 coins");
            }
            // check if there is an active tournament
            Tournament currTournament = tournamentStatusService.getTournament();
            if (!currTournament.getIsActive()) {
                return ResponseEntity.status(404).body("No active tournament found");
            }
            // TODO assign player to group (TournamentGroupService)
            // TODO format group to groupleaderboard
            // TODO decrease user coin by 1000
            return ResponseEntity.status(200).body("Player entered the tournament");

        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("playerId must be a number: " + playerId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }
}
