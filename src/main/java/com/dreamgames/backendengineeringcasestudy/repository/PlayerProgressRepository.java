package com.dreamgames.backendengineeringcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dreamgames.backendengineeringcasestudy.model.PlayerProgress;

import jakarta.transaction.Transactional;

@Repository

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {

    @Query("SELECT pp FROM PlayerProgress pp WHERE pp.playerId = ?1")
    // not need to be optional as id come from player will be used to find the
    PlayerProgress findByPlayerId(Long playerId);

    @Modifying
    @Query("UPDATE PlayerProgress pp SET pp.coin = pp.coin - 1000 WHERE pp.playerId = ?1")
    @Transactional
    public void decreaseThousandCoin(Long playerId);

    @Modifying
    @Query("UPDATE PlayerProgress pp SET pp.level = pp.level + 1 WHERE pp.playerId = ?1")
    @Transactional
    public void increaseLevel(Long playerId);

}
