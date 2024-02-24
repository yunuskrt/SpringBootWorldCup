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

    public TournamentStatusService(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(0, 0, 0);
        LocalTime endTime = LocalTime.of(23, 0, 0);

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

    @Scheduled(cron = "0 15 23 * * *")
    private void startTournament() {

        // add new tournament is_active = true
        Tournament tournamentToAdd = new Tournament(true);
        Tournament newTournament = tournamentService.addNewTournament(tournamentToAdd);
        this.tournament = newTournament;

    }

    @Scheduled(cron = "0 59 23 * * *")
    private void endTournament() {
        this.tournament = new Tournament(false);
        // deactivate old tournaments
        tournamentService.deActivateTournaments();
        // set related claim rewards to true for users
    }

    public Tournament getTournament() {
        return tournament;
    }

}
