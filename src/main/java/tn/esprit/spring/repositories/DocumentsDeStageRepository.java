package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.models.DocumentsDeStage;

public interface DocumentsDeStageRepository extends JpaRepository<DocumentsDeStage, Integer> {

	//Native Query
	@Query(value = "select * from documents_de_stage  where etat_demande = 'DEPOSEE'", nativeQuery = true)
	public List<DocumentsDeStage> getListDocumentsDeStageDEPOSEE();
}
