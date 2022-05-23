package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.models.Depot;

public interface DepotRepository extends JpaRepository<Depot, Integer> {

}
