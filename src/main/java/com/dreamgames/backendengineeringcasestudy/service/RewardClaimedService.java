package com.dreamgames.backendengineeringcasestudy.service;

import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.RewardClaimed;
import com.dreamgames.backendengineeringcasestudy.repository.RewardClaimedRepository;

@Service
public class RewardClaimedService {
    private final RewardClaimedRepository rewardClaimedRepository;

    public RewardClaimedService(RewardClaimedRepository rewardClaimedRepository) {
        this.rewardClaimedRepository = rewardClaimedRepository;
    }

    public void addNewRewardClaimed(RewardClaimed rewardClaimed) {
        rewardClaimedRepository.save(rewardClaimed);
    }
}
