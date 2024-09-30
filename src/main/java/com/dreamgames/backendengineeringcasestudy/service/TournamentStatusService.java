package com.dreamgames.backendengineeringcasestudy.service;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.Tournament;

@Service
public class TournamentStatusService {
    private Tournament tournament;
    private final TournamentService tournamentService;
    private final RewardClaimedService rewardClaimedService;

    public TournamentStatusService(TournamentService tournamentService, RewardClaimedService rewardClaimedService) {
        this.tournamentService = tournamentService;
        this.rewardClaimedService = rewardClaimedService;
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(0, 0, 0);
        LocalTime endTime = LocalTime.of(20, 0, 0);

        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            Optional<Tournament> activeTournament = tournamentService.getActiveTournament();
            if (activeTournament.isPresent()) {
                this.tournament = activeTournament.get();
            } else {
                this.tournament = new Tournament(false);
            }
        } else {
            this.tournament = new Tournament(false);
        }
    }

    @Scheduled(cron = "0 46 4 * * *")
    private void startTournament() {

        // add new tournament is_active = true
        Tournament tournamentToAdd = new Tournament(true);
        Tournament newTournament = tournamentService.addNewTournament(tournamentToAdd);
        this.tournament = newTournament;

    }

    @Scheduled(cron = "0 49 4 * * *")
    private void endTournament() {
        // assign rewards
        rewardClaimedService.assignRewardsToHighestPlayers(tournament.getId());
        this.tournament = new Tournament(false);
        // deactivate old tournaments
        tournamentService.deActivateTournaments();
        // set related claim rewards to true for users
    }

    public Tournament getTournament() {
        return tournament;
    }

}
