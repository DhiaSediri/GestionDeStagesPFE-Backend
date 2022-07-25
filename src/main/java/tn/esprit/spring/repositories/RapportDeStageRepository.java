package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.RapportDeStage;

@Repository
public interface RapportDeStageRepository extends JpaRepository<RapportDeStage, Integer> {}
