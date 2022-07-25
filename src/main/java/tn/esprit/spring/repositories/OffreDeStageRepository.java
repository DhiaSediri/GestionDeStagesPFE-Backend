package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.OffreDeStage;

@Repository
public interface OffreDeStageRepository extends JpaRepository<OffreDeStage, Integer> {}
