package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.models.Depot;

public interface DepotRepository extends JpaRepository<Depot, Integer> {

	//Native Query
	// sélectionner tous les dépots d'un etudiant
	@Query(value = "select * from depot where etudiant_id = id", nativeQuery = true)
	List<Depot> getListDepotByEtudiantId(@Param("id") Long id);
	
	//Delete Depot
	@Query(value = "delete from depot where etudiant_id = etudiantId and type_depot = 'Convention_de_stage'", nativeQuery = true)
	void deleteDepotConvention_de_stageByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Fiche_de_stage'", nativeQuery = true)
	void deleteDepotFiche_de_stageByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Bilan_périodique_début_du_stage'", nativeQuery = true)
	void deleteDepotBilan_périodique_début_du_stageByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Bilan_périodique_milieu_du_stage'", nativeQuery = true)
	void deleteDepotBilan_périodique_milieu_du_stageByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Bilan_périodique_fin_du_stage'", nativeQuery = true)
	void deleteDepotBilan_périodique_fin_du_stageByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Rapport_premiere_version'", nativeQuery = true)
	void deleteDepotRapport_premiere_versionByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Rapport_version_finale'", nativeQuery = true)
	void deleteDepotRapport_version_finaleByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	@Query(value = "delete from depot  where etudiant_id = etudiantId and type_depot = 'Journal_de_stage'", nativeQuery = true)
	void deleteDepotJournal_de_stageByEtudiantId(@Param("etudiantId") Long etudiantId);
	
	// sélectionner la liste des dépots Convention_de_stage pour supprimer un dépot Convention_de_stage et pour afficher la liste des dépots Convention_de_stage à l'Admin
	@Query(value = "select * from depot where type_depot = 'Convention_de_stage'", nativeQuery = true)
	List<Depot> getListDepotConvention_de_stage();
	
	@Query(value = "select * from depot where type_depot = 'Fiche_de_stage'", nativeQuery = true)
	List<Depot> getListDepotFiche_de_stage();
	
	@Query(value = "select * from depot where type_depot = 'Bilan_périodique_début_du_stage'", nativeQuery = true)
	List<Depot> getListDepotBilan_périodique_début_du_stage();
	
	@Query(value = "select * from depot where type_depot = 'Bilan_périodique_milieu_du_stage'", nativeQuery = true)
	List<Depot> getListDepotBilan_périodique_milieu_du_stage();
	
	@Query(value = "select * from depot where type_depot = 'Bilan_périodique_fin_du_stage'", nativeQuery = true)
	List<Depot> getListDepotBilan_périodique_fin_du_stage();
	
	@Query(value = "select * from depot where type_depot = 'Rapport_premiere_version'", nativeQuery = true)
	List<Depot> getListDepotRapport_premiere_version();
	
	@Query(value = "select * from depot where type_depot = 'Rapport_version_finale'", nativeQuery = true)
	List<Depot> getListDepotRapport_version_finale();
	
	@Query(value = "select * from depot where type_depot = 'Journal_de_stage'", nativeQuery = true)
	List<Depot> getListDepotJournal_de_stage();
	
	// sélectionner la liste des dépots Convention_de_stage DEPOSEE pour les traiter par l'Admin
	@Query(value = "select * from depot where type_depot = 'Convention_de_stage' and etat_depot = 'DEPOSEE'", nativeQuery = true)
	List<Depot> getListDepotConvention_de_stageDEPOSEE();
	
}
