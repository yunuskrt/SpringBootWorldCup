package com.dreamgames.backendengineeringcasestudy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamgames.backendengineeringcasestudy.model.Player;
import com.dreamgames.backendengineeringcasestudy.model.PlayerInfo;
import com.dreamgames.backendengineeringcasestudy.model.PlayerProgress;
import com.dreamgames.backendengineeringcasestudy.model.PlayerToAdd;
import com.dreamgames.backendengineeringcasestudy.model.RewardClaimed;
import com.dreamgames.backendengineeringcasestudy.model.Tournament;
import com.dreamgames.backendengineeringcasestudy.model.TournamentGroup;
import com.dreamgames.backendengineeringcasestudy.service.PlayerProgressService;
import com.dreamgames.backendengineeringcasestudy.service.PlayerService;
import com.dreamgames.backendengineeringcasestudy.service.RewardClaimedService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentGroupService;
import com.dreamgames.backendengineeringcasestudy.service.TournamentStatusService;

@RestController
@RequestMapping("api/v1/player")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerProgressService playerProgressService;
    private final RewardClaimedService rewardClaimedService;
    private final TournamentStatusService tournamentStatusService;
    private final TournamentGroupService tournamentGroupService;

    public PlayerController(PlayerService playerService, PlayerProgressService playerProgressService,
            RewardClaimedService rewardClaimedService, TournamentStatusService tournamentStatusService,
            TournamentGroupService tournamentGroupService) {
        this.playerService = playerService;
        this.playerProgressService = playerProgressService;
        this.rewardClaimedService = rewardClaimedService;
        this.tournamentStatusService = tournamentStatusService;
        this.tournamentGroupService = tournamentGroupService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<?> addPlayer(@RequestBody PlayerToAdd playerToAdd) {
        try {
            String username = playerToAdd.getUsername();
            // input validation
            if (username == null || username.isEmpty()) {
                return ResponseEntity.status(400).body("Username is required");
            }
            Player playerToSave = new Player(playerToAdd);

            Player createdPlayer = playerService.addNewPlayer(playerToSave);

            // get created player's id
            Long playerId = createdPlayer.getId();

            // create player progress for created player
            PlayerProgress playerProgress = new PlayerProgress(playerId);
            playerProgressService.addNewPlayerProgress(playerProgress);

            // create reward claimed for created player
            rewardClaimedService.addNewRewardClaimed(new RewardClaimed(playerId));

            PlayerInfo createdPlayerInfo = new PlayerInfo(createdPlayer, playerProgress);

            return ResponseEntity.status(201).body(createdPlayerInfo);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }

    @GetMapping("{playerId}")
    public String getPlayerWithId(@PathVariable String playerId) {
        try {
            Long id = Long.parseLong(playerId);
            return id.toString();
        } catch (NumberFormatException e) {
            return "Player id must be a number";
        } catch (Exception e) {
            return "An error occurred";
        }
    }

    @PutMapping("level/{playerId}")
    public ResponseEntity<?> updateLevel(@PathVariable Long playerId) {
        try {
            // check if player exists
            Optional<Player> optionalPlayer = playerService.getPlayerById(playerId);
            if (!optionalPlayer.isPresent()) {
                return ResponseEntity.status(404).body("Player not found");
            }
            // update level
            playerProgressService.updateLevel(playerId);
            PlayerProgress updatedPlayerProgress = playerProgressService.getByPlayerId(playerId);

            Tournament tournament = tournamentStatusService.getTournament();
            if (tournament.getIsActive()) {
                Optional<TournamentGroup> optionalGroup = tournamentGroupService.getActiveGroupForPlayer(
                        tournament.getId(),
                        playerId);
                // check if player is in a started group
                if (optionalGroup.isPresent()) {
                    TournamentGroup group = optionalGroup.get();
                    // update user score based on country
                    Player player = optionalPlayer.get();
                    tournamentGroupService.increaseUserScore(player, group.getId());
                }
            }
            return ResponseEntity.status(201).body(updatedPlayerProgress);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }

}
