package com.dreamgames.backendengineeringcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dreamgames.backendengineeringcasestudy.model.PlayerProgress;

@Repository

public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {

}
