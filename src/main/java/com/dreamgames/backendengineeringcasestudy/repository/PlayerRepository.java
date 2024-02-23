package com.dreamgames.backendengineeringcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dreamgames.backendengineeringcasestudy.model.Player;

@Repository

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
