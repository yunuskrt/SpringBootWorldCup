package com.dreamgames.backendengineeringcasestudy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dreamgames.backendengineeringcasestudy.model.Tournament;

import jakarta.transaction.Transactional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // method to find active tournament
    @Query("SELECT t FROM Tournament t WHERE t.isActive = true")
    Optional<Tournament> findActiveTournament();

    @Modifying
    @Query("UPDATE Tournament t SET t.isActive = false WHERE t.isActive = true")
    @Transactional
    void deActivateTournaments();

}
