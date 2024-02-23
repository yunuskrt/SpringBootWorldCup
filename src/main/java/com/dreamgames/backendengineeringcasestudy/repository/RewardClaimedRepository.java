package com.dreamgames.backendengineeringcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dreamgames.backendengineeringcasestudy.model.RewardClaimed;

@Repository

public interface RewardClaimedRepository extends JpaRepository<RewardClaimed, Long> {

}
