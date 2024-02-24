package com.dreamgames.backendengineeringcasestudy.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dreamgames.backendengineeringcasestudy.model.Player;
import com.dreamgames.backendengineeringcasestudy.model.TournamentGroup;
import com.dreamgames.backendengineeringcasestudy.repository.TournamentGroupRepository;

@Service
public class TournamentGroupService {
    private final TournamentGroupRepository tournamentGroupRepository;

    public TournamentGroupService(TournamentGroupRepository tournamentGroupRepository) {
        this.tournamentGroupRepository = tournamentGroupRepository;
    }

    // save player to group dynamically by country
    public TournamentGroup assignPlayerToTournament(Player player, Long tournamentId) {
        String country = player.getCountry();
        Long playerId = player.getId();
        if (country.equals("Turkey")) {
            Optional<TournamentGroup> optionalGroup = tournamentGroupRepository.findGroupForTurkishPlayer();
            if (optionalGroup.isPresent()) {
                TournamentGroup group = optionalGroup.get();
                // update turkish column in group table
                tournamentGroupRepository.saveTurkishPlayer(playerId, group.getId());
                group.setTurkishPlayer(playerId);
                return group;
            }
            // construct new group with only turkish player
            TournamentGroup groupToAdd = new TournamentGroup(tournamentId, playerId.intValue(), 0, 0, 0, 0);
            return tournamentGroupRepository.save(groupToAdd);

        } else if (country.equals("United States")) {
            Optional<TournamentGroup> optionalGroup = tournamentGroupRepository.findGroupForUSPlayer();
            if (optionalGroup.isPresent()) {
                TournamentGroup group = optionalGroup.get();
                // update us column in group table
                tournamentGroupRepository.saveUSPlayer(playerId, group.getId());
                group.setUsPlayer(playerId);
                return group;
            }
            // construct new group with only us player
            TournamentGroup groupToAdd = new TournamentGroup(tournamentId, 0, playerId.intValue(), 0, 0, 0);
            return tournamentGroupRepository.save(groupToAdd);
        } else if (country.equals("United Kingdom")) {
            Optional<TournamentGroup> optionalGroup = tournamentGroupRepository.findGroupForUKPlayer();
            if (optionalGroup.isPresent()) {
                TournamentGroup group = optionalGroup.get();
                // update uk column in group table
                tournamentGroupRepository.saveUKPlayer(playerId, group.getId());
                group.setUkPlayer(playerId);
                return group;
            }
            // construct new group with only uk player
            TournamentGroup groupToAdd = new TournamentGroup(tournamentId, 0, 0, playerId.intValue(), 0, 0);
            return tournamentGroupRepository.save(groupToAdd);
        } else if (country.equals("France")) {
            Optional<TournamentGroup> optionalGroup = tournamentGroupRepository.findGroupForFrenchPlayer();
            if (optionalGroup.isPresent()) {
                TournamentGroup group = optionalGroup.get();
                // update french column in group table
                tournamentGroupRepository.saveFrenchPlayer(playerId, group.getId());
                group.setFrenchPlayer(playerId);
                return group;
            }
            // construct new group with only french player
            TournamentGroup groupToAdd = new TournamentGroup(tournamentId, 0, 0, 0, playerId.intValue(), 0);
            return tournamentGroupRepository.save(groupToAdd);
        }
        Optional<TournamentGroup> optionalGroup = tournamentGroupRepository.findGroupForGermanPlayer();
        if (optionalGroup.isPresent()) {
            TournamentGroup group = optionalGroup.get();
            // update german column in group table
            tournamentGroupRepository.saveGermanPlayer(playerId, group.getId());
            group.setGermanPlayer(playerId);
            return group;
        }
        // construct new group with only german player
        TournamentGroup groupToAdd = new TournamentGroup(tournamentId, 0, 0, 0, 0, playerId.intValue());
        return tournamentGroupRepository.save(groupToAdd);

    }
}
