package com.dreamgames.backendengineeringcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dreamgames.backendengineeringcasestudy.model.PlayerProgress;

@Repository

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {

    @Query("SELECT pp FROM PlayerProgress pp WHERE pp.playerId = ?1")
    // not need to be optional as id come from player will be used to find the
    // progress
    PlayerProgress findByPlayerId(Long playerId);
}
