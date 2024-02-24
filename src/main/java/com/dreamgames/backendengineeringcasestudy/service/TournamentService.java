package com.dreamgames.backendengineeringcasestudy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.Tournament;
import com.dreamgames.backendengineeringcasestudy.repository.TournamentRepository;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public Optional<Tournament> getActiveTournament() {
        return tournamentRepository.findActiveTournament();
    }

    public void deActivateTournaments() {
        tournamentRepository.deActivateTournaments();
    }

    public Tournament addNewTournament(Tournament tournament) {
        Tournament createdTournament = tournamentRepository.save(tournament);
        return createdTournament;
    }
}
