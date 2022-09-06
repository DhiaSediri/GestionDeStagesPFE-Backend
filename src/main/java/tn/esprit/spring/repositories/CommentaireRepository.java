package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.Commentaire;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

}
