package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.DocumentsDeStage;

@Repository
public interface DocumentsDeStageRepository extends JpaRepository<DocumentsDeStage, Integer> {

	//Native Query
	//sélectionner la liste des demandes de stage DEPOSEE pour les traiter par l'Admin
	@Query(value = "select * from documents_de_stage  where etat_demande = 'DEPOSEE'", nativeQuery = true)
	public List<DocumentsDeStage> getListDocumentsDeStageDEPOSEE();
	
}
