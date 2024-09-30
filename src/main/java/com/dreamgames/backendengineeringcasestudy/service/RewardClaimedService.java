package com.dreamgames.backendengineeringcasestudy.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.RewardClaimed;
import com.dreamgames.backendengineeringcasestudy.model.TournamentGroup;
import com.dreamgames.backendengineeringcasestudy.repository.RewardClaimedRepository;

@Service
public class RewardClaimedService {
    private final RewardClaimedRepository rewardClaimedRepository;
    private final TournamentGroupService tournamentGroupService;

    public RewardClaimedService(RewardClaimedRepository rewardClaimedRepository,
            TournamentGroupService tournamentGroupService) {
        this.rewardClaimedRepository = rewardClaimedRepository;
        this.tournamentGroupService = tournamentGroupService;
    }

    public void addNewRewardClaimed(RewardClaimed rewardClaimed) {
        rewardClaimedRepository.save(rewardClaimed);
    }

    public void assignRewardsToHighestPlayers(Long tournamentId) {
        List<Long> players = new ArrayList<>();
        List<TournamentGroup> groups = tournamentGroupService.getActiveGroupsForTournament(tournamentId);
        for (TournamentGroup group : groups) {

            Map<Long, Integer> groupMap = Map.of(group.getTurkishPlayer(), group.getTurkishScore(), group.getUsPlayer(),
                    group.getUsScore(), group.getUkPlayer(), group.getUkScore(), group.getFrenchPlayer(),
                    group.getFrenchScore(), group.getGermanPlayer(), group.getGermanScore());

            // sort map by value
            Stream<Map.Entry<Long, Integer>> sortedMap = groupMap.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
            // get first key
            Long firstId = sortedMap.findFirst().get().getKey();
            // get second key
            Long secondId = sortedMap.skip(1).findFirst().get().getKey();

            players.add(firstId);
            players.add(secondId);
        }
        rewardClaimedRepository.setRewardClaimed(players);
    }

    public boolean isPlayerClaimedReward(Long playerId) {
        return rewardClaimedRepository.findPlayerWithClaimedReward(playerId).isPresent();
    }

}