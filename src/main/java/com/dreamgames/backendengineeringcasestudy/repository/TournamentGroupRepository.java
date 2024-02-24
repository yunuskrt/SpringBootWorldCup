package com.dreamgames.backendengineeringcasestudy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dreamgames.backendengineeringcasestudy.model.TournamentGroup;

import jakarta.transaction.Transactional;

@Repository
public interface TournamentGroupRepository extends JpaRepository<TournamentGroup, Long> {

    // 0 considered as null meaning no player for that column
    // methods to find oldest group based on country
    @Query("SELECT tg FROM TournamentGroup tg WHERE tg.turkishPlayer = 0 ORDER BY tg.createdAt ASC LIMIT 1")
    Optional<TournamentGroup> findGroupForTurkishPlayer();

    @Query("SELECT tg FROM TournamentGroup tg WHERE tg.usPlayer = 0 ORDER BY tg.createdAt ASC LIMIT 1")
    Optional<TournamentGroup> findGroupForUSPlayer();

    @Query("SELECT tg FROM TournamentGroup tg WHERE tg.ukPlayer = 0 ORDER BY tg.createdAt ASC LIMIT 1")
    Optional<TournamentGroup> findGroupForUKPlayer();

    @Query("SELECT tg FROM TournamentGroup tg WHERE tg.frenchPlayer = 0 ORDER BY tg.createdAt ASC LIMIT 1")
    Optional<TournamentGroup> findGroupForFrenchPlayer();

    @Query("SELECT tg FROM TournamentGroup tg WHERE tg.germanPlayer = 0 ORDER BY tg.createdAt ASC LIMIT 1")
    Optional<TournamentGroup> findGroupForGermanPlayer();

    @Modifying
    @Query("UPDATE TournamentGroup tg SET tg.turkishPlayer = ?1 WHERE tg.id = ?2")
    @Transactional
    void saveTurkishPlayer(Long playerId, Long groupId);

    @Modifying
    @Query("UPDATE TournamentGroup tg SET tg.usPlayer = ?1 WHERE tg.id = ?2")
    @Transactional
    void saveUSPlayer(Long playerId, Long groupId);

    @Modifying
    @Query("UPDATE TournamentGroup tg SET tg.ukPlayer = ?1 WHERE tg.id = ?2")
    @Transactional
    void saveUKPlayer(Long playerId, Long groupId);

    @Modifying
    @Query("UPDATE TournamentGroup tg SET tg.frenchPlayer = ?1 WHERE tg.id = ?2")
    @Transactional
    void saveFrenchPlayer(Long playerId, Long groupId);

    @Modifying
    @Query("UPDATE TournamentGroup tg SET tg.germanPlayer = ?1 WHERE tg.id = ?2")
    @Transactional
    void saveGermanPlayer(Long playerId, Long groupId);

}
