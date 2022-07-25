package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.models.Depot;
import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.models.Etat;
import tn.esprit.spring.models.User;
import tn.esprit.spring.services.DepotService;
import tn.esprit.spring.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/depot")
public class DepotController {

	@Autowired
	DepotService depotService;

	@Autowired
	UserService userService;
	
	///////////////////////////////////////////// List Depot /////////////////////////////////////////////
	
	@GetMapping("/getDepotConvention_de_stageListParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Depot> fetchDepotConvention_de_stageListParRecherche(@PathVariable String mots_cles) {
		
		List<Depot> convention_de_stageList = new ArrayList<Depot>();
		convention_de_stageList = depotService.fetchDepotConvention_de_stageList();
		
		List<Depot> convention_de_stageListParRecherche = new ArrayList<Depot>();
		
		for (Depot convention_de_stage : convention_de_stageList) {
			if(convention_de_stage.getEtudiant().getUsername().contains(mots_cles) || convention_de_stage.getEtudiant().getEmail().contains(mots_cles)) {	
				convention_de_stageListParRecherche.add(convention_de_stage);
			}	
		}
		return convention_de_stageListParRecherche;
	}
	
	@GetMapping("/getDepotConvention_de_stageList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Depot> fetchDepotConvention_de_stageList() {

		List<Depot> depotConvention_de_stageList = new ArrayList<Depot>();
		depotConvention_de_stageList = depotService.fetchDepotConvention_de_stageList();
		return depotConvention_de_stageList;
	}

	@GetMapping("/getDepotList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Depot> fetchDepotList() {

		List<Depot> depotList = new ArrayList<Depot>();
		depotList = depotService.fetchDepotList();
		return depotList;
	}
	
	///////////////////////////////////////////// Delete Depot /////////////////////////////////////////////

	@DeleteMapping("/deleteDepotConvention_de_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotConvention_de_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotConvention_de_stageByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotFiche_de_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotFiche_de_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotFiche_de_stageByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotBilan_périodique_début_du_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotBilan_périodique_début_du_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotBilan_périodique_début_du_stageByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotBilan_périodique_milieu_du_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotBilan_périodique_milieu_du_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotBilan_périodique_milieu_du_stageByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotBilan_périodique_fin_du_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotBilan_périodique_fin_du_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotBilan_périodique_fin_du_stageByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotRapport_premiere_versionByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotRapport_premiere_versionByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotRapport_premiere_versionByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotRapport_version_finaleByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotRapport_version_finaleByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotRapport_version_finaleByEtudiantId(etudiantId);
	}

	@DeleteMapping("/deleteDepotJournal_de_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotJournal_de_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.deleteDepotJournal_de_stageByEtudiantId(etudiantId);
	}
	
	///////////////////////////////////////////// Add Depot /////////////////////////////////////////////

	@PostMapping("/addDepotConvention_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotConvention_de_stage(@RequestBody Long etudiantId) {

		return depotService.saveDepotConvention_de_stage(etudiantId);
	}

	@PostMapping("/addDepotFiche_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotFiche_de_stage(@RequestBody Long etudiantId) {

		return depotService.saveDepotFiche_de_stage(etudiantId);
	}

	@PostMapping("/addDepotBilan_périodique_début_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotBilan_périodique_début_du_stage(@RequestBody Long etudiantId) {

		return depotService.saveDepotBilan_périodique_début_du_stage(etudiantId);
	}

	@PostMapping("/addDepotBilan_périodique_milieu_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotBilan_périodique_milieu_du_stage(@RequestBody Long etudiantId) {

		return depotService.saveDepotBilan_périodique_milieu_du_stage(etudiantId);
	}

	@PostMapping("/addDepotBilan_périodique_fin_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotBilan_périodique_fin_du_stage(@RequestBody Long etudiantId) {

		return depotService.saveDepotBilan_périodique_fin_du_stage(etudiantId);
	}

	@PostMapping("/addDepotRapport_premiere_version")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotRapport_premiere_version(@RequestBody Long etudiantId) {

		return depotService.saveDepotRapport_premiere_version(etudiantId);
	}

	@PostMapping("/addDepotRapport_version_finale")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotRapport_version_finale(@RequestBody Long etudiantId) {

		return depotService.saveDepotRapport_version_finale(etudiantId);
	}

	@PostMapping("/addDepotJournal_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotJournal_de_stage(@RequestBody Long etudiantId) {

		return depotService.saveDepotJournal_de_stage(etudiantId);
	}
	
	///////////////////////////////////////////// Details Depot /////////////////////////////////////////////

	@GetMapping("/getDetailsDepotConvention_de_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotConvention_de_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotConvention_de_stageByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotFiche_de_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotFiche_de_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotFiche_de_stageByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotBilan_périodique_début_du_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotBilan_périodique_début_du_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotBilan_périodique_début_du_stageByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotRapport_premiere_versionByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotRapport_premiere_versionByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotRapport_premiere_versionByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotRapport_version_finaleByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotRapport_version_finaleByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotRapport_version_finaleByEtudiantId(etudiantId);
	}

	@GetMapping("/getDetailsDepotJournal_de_stageByEtudiantId/{etudiantId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot getDetailsDepotJournal_de_stageByEtudiantId(@PathVariable Long etudiantId) {

		return depotService.getDetailsDepotJournal_de_stageByEtudiantId(etudiantId);
	}
	
	///////////////////////////////////////////// Traitement Depot /////////////////////////////////////////////
	
	@GetMapping("/getListDepotConvention_de_stageDEPOSEEParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Depot> fetchListDepotConvention_de_stageDEPOSEEParRecherche(@PathVariable String mots_cles) {
		
		List<Depot> listConvention_de_stageDEPOSEE = new ArrayList<Depot>();
		listConvention_de_stageDEPOSEE = depotService.fetchDepotConvention_de_stageDEPOSEEList();
		
		List<Depot> listConvention_de_stageDEPOSEEParRecherche = new ArrayList<Depot>();
		
		for (Depot convention_de_stage : listConvention_de_stageDEPOSEE) {
			if(convention_de_stage.getEtudiant().getUsername().contains(mots_cles) || convention_de_stage.getEtudiant().getEmail().contains(mots_cles)) {	
				listConvention_de_stageDEPOSEEParRecherche.add(convention_de_stage);
			}	
		}
		return listConvention_de_stageDEPOSEEParRecherche;
	}
	
	@GetMapping("/getDepotConvention_de_stageDEPOSEEList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Depot> fetchDepotConvention_de_stageDEPOSEEList() {

		List<Depot> depotConvention_de_stageDEPOSEEList = new ArrayList<Depot>();
		depotConvention_de_stageDEPOSEEList = depotService.fetchDepotConvention_de_stageDEPOSEEList();
		return depotConvention_de_stageDEPOSEEList;
	}

	@PostMapping("/accepterDepotConventionDeStage/{depot_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot accepterDepotConventionDeStage(@PathVariable int depot_id) {

		Depot depotConventionDeStage = depotService.fetchDepotById(depot_id).get();
		depotConventionDeStage.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotConventionDeStage);
	}

	@PostMapping("/refuserDepotConventionDeStage/{depot_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot refuserDepotConventionDeStage(@PathVariable int depot_id) {

		Depot depotConventionDeStage = depotService.fetchDepotById(depot_id).get();
		depotConventionDeStage.setEtatDepot(Etat.REFUSEE);
		return depotService.updateEtatDepot(depotConventionDeStage);
	}

	@GetMapping("/declarerTraitementDepotFiche_de_stage/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotFiche_de_stage(@PathVariable Long etudiant_id) {
		Depot depotFiche_de_stage = getDetailsDepotFiche_de_stageByEtudiantId(etudiant_id);
		depotFiche_de_stage.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotFiche_de_stage);
	}

	@GetMapping("/declarerTraitementDepotBilan_périodique_début_du_stage/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotBilan_périodique_début_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_début_du_stage = getDetailsDepotBilan_périodique_début_du_stageByEtudiantId(
				etudiant_id);
		depotBilan_périodique_début_du_stage.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotBilan_périodique_début_du_stage);
	}

	@GetMapping("/declarerTraitementDepotBilan_périodique_milieu_du_stage/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotBilan_périodique_milieu_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_milieu_du_stage = getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId(
				etudiant_id);
		depotBilan_périodique_milieu_du_stage.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotBilan_périodique_milieu_du_stage);
	}

	@GetMapping("/declarerTraitementDepotBilan_périodique_fin_du_stage/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotBilan_périodique_fin_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_fin_du_stage = getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId(
				etudiant_id);
		depotBilan_périodique_fin_du_stage.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotBilan_périodique_fin_du_stage);
	}

	@GetMapping("/declarerTraitementDepotRapport_premiere_version/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotRapport_premiere_version(@PathVariable Long etudiant_id) {
		Depot depotRapport_premiere_version = getDetailsDepotRapport_premiere_versionByEtudiantId(etudiant_id);
		depotRapport_premiere_version.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotRapport_premiere_version);
	}

	@GetMapping("/declarerTraitementDepotRapport_version_finale/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotRapport_version_finale(@PathVariable Long etudiant_id) {
		Depot depotRapport_version_finale = getDetailsDepotRapport_version_finaleByEtudiantId(etudiant_id);
		depotRapport_version_finale.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotRapport_version_finale);
	}

	@GetMapping("/declarerTraitementDepotJournal_de_stage/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot declarerTraitementDepotJournal_de_stage(@PathVariable Long etudiant_id) {
		Depot depotJournal_de_stage = getDetailsDepotJournal_de_stageByEtudiantId(etudiant_id);
		depotJournal_de_stage.setEtatDepot(Etat.TRAITEE);
		return depotService.updateEtatDepot(depotJournal_de_stage);
	}

	/////////////////////////// Tester l'existance et l'état de la Demande De Stage /////////////////////////////////

	@GetMapping("/existeDemandeDeStage/{etudiant_id}")
	public boolean existeDemandeDeStage(@PathVariable Long etudiant_id) {
		User etudiant = userService.fetchUserById(etudiant_id).get();
		DocumentsDeStage demande = etudiant.getDemandeDeStage();

		if (demande != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatDemandeDeStage/{etudiant_id}")
	public int etatDemandeDeStage(@PathVariable Long etudiant_id) {
		User etudiant = userService.fetchUserById(etudiant_id).get();
		DocumentsDeStage demande = etudiant.getDemandeDeStage();

		if (demande.getEtatDemande() == Etat.DEPOSEE) {
			return 1;
		}

		if (demande.getEtatDemande() == Etat.TRAITEE) {
			return 2;
		}
		// if(demande.getEtatDemande() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état de la Convention_de_stage /////////////////////////////////

	@GetMapping("/existeConvention_de_stage/{etudiant_id}")
	public boolean existeConvention_de_stage(@PathVariable Long etudiant_id) {
		Depot depotConvention_de_stage = depotService.getDetailsDepotConvention_de_stageByEtudiantId(etudiant_id);

		if (depotConvention_de_stage != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatConvention_de_stage/{etudiant_id}")
	public int etatConvention_de_stage(@PathVariable Long etudiant_id) {
		Depot depotConvention_de_stage = depotService.getDetailsDepotConvention_de_stageByEtudiantId(etudiant_id);

		if (depotConvention_de_stage.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotConvention_de_stage.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotConvention_de_stage.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état de la Fiche_de_stage /////////////////////////////////

	@GetMapping("/existeFiche_de_stage/{etudiant_id}")
	public boolean existeFiche_de_stage(@PathVariable Long etudiant_id) {
		Depot depotFiche_de_stage = depotService.getDetailsDepotFiche_de_stageByEtudiantId(etudiant_id);

		if (depotFiche_de_stage != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatFiche_de_stage/{etudiant_id}")
	public int etatFiche_de_stage(@PathVariable Long etudiant_id) {
		Depot depotFiche_de_stage = depotService.getDetailsDepotFiche_de_stageByEtudiantId(etudiant_id);

		if (depotFiche_de_stage.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotFiche_de_stage.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotFiche_de_stage.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état du Bilan_périodique_début_du_stage///////////////////////////

	@GetMapping("/existeBilan_périodique_début_du_stage/{etudiant_id}")
	public boolean existeBilan_périodique_début_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_début_du_stage = depotService
				.getDetailsDepotBilan_périodique_début_du_stageByEtudiantId(etudiant_id);

		if (depotBilan_périodique_début_du_stage != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatBilan_périodique_début_du_stage/{etudiant_id}")
	public int etatBilan_périodique_début_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_début_du_stage = depotService
				.getDetailsDepotBilan_périodique_début_du_stageByEtudiantId(etudiant_id);

		if (depotBilan_périodique_début_du_stage.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotBilan_périodique_début_du_stage.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotBilan_périodique_début_du_stage.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état du Bilan_périodique_milieu_du_stage ///////////////////////////

	@GetMapping("/existeBilan_périodique_milieu_du_stage/{etudiant_id}")
	public boolean existeBilan_périodique_milieu_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_milieu_du_stage = depotService
				.getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId(etudiant_id);

		if (depotBilan_périodique_milieu_du_stage != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatBilan_périodique_milieu_du_stage/{etudiant_id}")
	public int etatBilan_périodique_milieu_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_milieu_du_stage = depotService
				.getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId(etudiant_id);

		if (depotBilan_périodique_milieu_du_stage.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotBilan_périodique_milieu_du_stage.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotBilan_périodique_milieu_du_stage.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état du Bilan_périodique_fin_du_stage /////////////////////////////////

	@GetMapping("/existeBilan_périodique_fin_du_stage/{etudiant_id}")
	public boolean existeBilan_périodique_fin_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_fin_du_stage = depotService
				.getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId(etudiant_id);

		if (depotBilan_périodique_fin_du_stage != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatBilan_périodique_fin_du_stage/{etudiant_id}")
	public int etatBilan_périodique_fin_du_stage(@PathVariable Long etudiant_id) {
		Depot depotBilan_périodique_fin_du_stage = depotService
				.getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId(etudiant_id);

		if (depotBilan_périodique_fin_du_stage.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotBilan_périodique_fin_du_stage.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotBilan_périodique_fin_du_stage.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état du Rapport_premiere_version /////////////////////////////////

	@GetMapping("/existeRapport_premiere_version/{etudiant_id}")
	public boolean existeRapport_premiere_version(@PathVariable Long etudiant_id) {
		Depot depotRapport_premiere_version = depotService
				.getDetailsDepotRapport_premiere_versionByEtudiantId(etudiant_id);

		if (depotRapport_premiere_version != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatRapport_premiere_version/{etudiant_id}")
	public int etatRapport_premiere_version(@PathVariable Long etudiant_id) {
		Depot depotRapport_premiere_version = depotService
				.getDetailsDepotRapport_premiere_versionByEtudiantId(etudiant_id);

		if (depotRapport_premiere_version.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotRapport_premiere_version.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotRapport_premiere_version.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état du Rapport_version_finale /////////////////////////////////

	@GetMapping("/existeRapport_version_finale/{etudiant_id}")
	public boolean existeRapport_version_finale(@PathVariable Long etudiant_id) {
		Depot depotRapport_version_finale = depotService.getDetailsDepotRapport_version_finaleByEtudiantId(etudiant_id);

		if (depotRapport_version_finale != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatRapport_version_finale/{etudiant_id}")
	public int etatRapport_version_finale(@PathVariable Long etudiant_id) {
		Depot depotRapport_version_finale = depotService.getDetailsDepotRapport_version_finaleByEtudiantId(etudiant_id);

		if (depotRapport_version_finale.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotRapport_version_finale.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotRapport_version_finale.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

	/////////////////////////// Tester l'existance et l'état du Journal_de_stage /////////////////////////////////

	@GetMapping("/existeJournal_de_stage/{etudiant_id}")
	public boolean existeJournal_de_stage(@PathVariable Long etudiant_id) {
		Depot depotJournal_de_stage = depotService.getDetailsDepotJournal_de_stageByEtudiantId(etudiant_id);

		if (depotJournal_de_stage != null) {
			return true;
		}
		return false;
	}

	@GetMapping("/etatJournal_de_stage/{etudiant_id}")
	public int etatJournal_de_stage(@PathVariable Long etudiant_id) {
		Depot depotJournal_de_stage = depotService.getDetailsDepotJournal_de_stageByEtudiantId(etudiant_id);

		if (depotJournal_de_stage.getEtatDepot() == Etat.DEPOSEE) {
			return 1;
		}

		if (depotJournal_de_stage.getEtatDepot() == Etat.TRAITEE) {
			return 2;
		}
		// if(depotJournal_de_stage.getEtatDepot() == Etat.REFUSEE)
		return 0;
	}

}
