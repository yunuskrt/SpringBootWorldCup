package com.dreamgames.backendengineeringcasestudy.service;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TournamentStatusService {
    private boolean tournamentActive;

    public TournamentStatusService() {
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(0, 0, 0);
        LocalTime endTime = LocalTime.of(20, 0, 0);

        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            this.tournamentActive = true;
        } else {
            this.tournamentActive = false;
        }
        System.out.println(tournamentActive);
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void startTournament() {
        this.tournamentActive = true;
    }

    @Scheduled(cron = "0 0 20 * * *")
    private void endTournament() {
        this.tournamentActive = false;
        System.out.println(tournamentActive);
    }

    public boolean getTournamentStatus() {
        return tournamentActive;
    }

}
