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
import com.dreamgames.backendengineeringcasestudy.model.TournamentGroup;
import com.dreamgames.backendengineeringcasestudy.service.PlayerProgressService;
import com.dreamgames.backendengineeringcasestudy.service.PlayerService;
import com.dreamgames.backendengineeringcasestudy.service.RewardClaimedService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentGroupService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentStatusService;

@RestController
@RequestMapping("api/v1/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final PlayerService playerService;
    private final TournamentStatusService tournamentStatusService;
    private final TournamentGroupService tournamentGroupService;
    private final PlayerProgressService playerProgressService;
    private final RewardClaimedService rewardClaimedService;

    public TournamentController(TournamentService tournamentService, PlayerService playerService,
            TournamentStatusService tournamentStatusService, PlayerProgressService playerProgressService,
            TournamentGroupService tournamentGroupService, RewardClaimedService rewardClaimedService) {
        this.tournamentService = tournamentService;
        this.playerService = playerService;
        this.tournamentStatusService = tournamentStatusService;
        this.tournamentGroupService = tournamentGroupService;
        this.playerProgressService = playerProgressService;
        this.rewardClaimedService = rewardClaimedService;
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
                return ResponseEntity.status(400).body("No active tournament found");
            }
            // check if user is already in active tournament
            if (tournamentGroupService.isPlayerInActiveTournament(currTournament.getId(), player.getId())) {
                return ResponseEntity.status(400)
                        .body("Player with id " + playerId + " is already in the active tournament");
            }
            // TODO check if user claimed last tournaments reward
            if (!rewardClaimedService.isPlayerClaimedReward(player.getId())) {
                return ResponseEntity.status(400)
                        .body("Player with id " + playerId + " not claimed reward from previous tournament");
            }

            // decrease user coin by 1000
            playerProgressService.payTournamentEntryPrice(player.getId());
            // assign player to group (TournamentGroupService)
            TournamentGroup assignedGroup = tournamentGroupService.assignPlayerToTournament(player,
                    currTournament.getId());
            return ResponseEntity.status(201).body(assignedGroup);
            // TODO format group to groupleaderboard

        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("playerId must be a number: " + playerId);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }
}
