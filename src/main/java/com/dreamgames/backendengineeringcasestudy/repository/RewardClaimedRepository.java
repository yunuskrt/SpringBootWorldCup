package com.dreamgames.backendengineeringcasestudy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dreamgames.backendengineeringcasestudy.model.RewardClaimed;

import jakarta.transaction.Transactional;

@Repository

public interface RewardClaimedRepository extends JpaRepository<RewardClaimed, Long> {

    @Modifying
    @Query("UPDATE RewardClaimed rc SET rc.isClaimed = false WHERE rc.playerId IN :ids")
    @Transactional
    void setRewardClaimed(List<Long> ids);

    @Query("SELECT rc FROM RewardClaimed rc WHERE rc.isClaimed = true AND rc.playerId = :playerId")
    Optional<RewardClaimed> findPlayerWithClaimedReward(Long playerId);

}
