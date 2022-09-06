package tn.esprit.spring.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.Depot;
import tn.esprit.spring.models.Etat;
import tn.esprit.spring.models.TypeDepot;
import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.DepotRepository;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class DepotService {

	@Autowired
	DepotRepository depotRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	///////////////////////////////////////////// Traitement Depot /////////////////////////////////////////////
	
	public Optional<Depot> fetchDepotById(int id) {

		return depotRepository.findById(id);
	}

	public Depot updateEtatDepot(Depot depot) {

		return depotRepository.save(depot);
	}
	
	///////////////////////////////////////////// List Depot /////////////////////////////////////////////
	
	public List<Depot> fetchDepotList() {

		return depotRepository.findAll();
	}

	public List<Depot> fetchDepotConvention_de_stageList() {

		return depotRepository.getListDepotConvention_de_stage();
	}

	public List<Depot> fetchDepotConvention_de_stageDEPOSEEList() {

		return depotRepository.getListDepotConvention_de_stageDEPOSEE();
	}

	///////////////////////////////////////////// Delete Depot /////////////////////////////////////////////

	public String deleteDepotConvention_de_stageByEtudiantId(Long etudiantId) {
		List<Depot> listDepot = depotRepository.getListDepotConvention_de_stage();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotFiche_de_stageByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotFiche_de_stage();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotBilan_périodique_début_du_stageByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotBilan_périodique_début_du_stage();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotBilan_périodique_milieu_du_stageByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotBilan_périodique_milieu_du_stage();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotBilan_périodique_fin_du_stageByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotBilan_périodique_fin_du_stage();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotRapport_premiere_versionByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotRapport_premiere_version();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotRapport_version_finaleByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotRapport_version_finale();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}

	public String deleteDepotJournal_de_stageByEtudiantId(Long etudiantId) {

		List<Depot> listDepot = depotRepository.getListDepotJournal_de_stage();
		String result = "";
		for (Depot depot : listDepot) {
			if (depot.getEtudiant().getId() == etudiantId) {
				try {
					depotRepository.deleteById(depot.getId());
					result = "Depot with id " + depot.getId() + " successfully deleted";
				} catch (Exception e) {
					result = "Depot with id " + depot.getId() + " is not deleted";
				}
			}
		}
		return result;
	}
	
	///////////////////////////////////////////// Add Depot /////////////////////////////////////////////

	public Depot saveDepotConvention_de_stage(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Convention_de_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);
		
		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotFiche_de_stage(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Fiche_de_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotBilan_périodique_début_du_stage(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Bilan_périodique_début_du_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotBilan_périodique_milieu_du_stage(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Bilan_périodique_milieu_du_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotBilan_périodique_fin_du_stage(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Bilan_périodique_fin_du_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotRapport_premiere_version(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Rapport_premiere_version);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotRapport_version_finale(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Rapport_version_finale);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}

	public Depot saveDepotJournal_de_stage(Long etudiantId) {

		Depot depot = new Depot();
		
		depot.setTypeDepot(TypeDepot.Journal_de_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		depot.setToOrderDateDepot(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    depot.setToDisplayDateDepot(formattedDate);

		List<Depot> listDepots = new ArrayList<Depot>();
		listDepots.add(depot);

		User etudiant = userRepository.findById(etudiantId).get();
		depot.setEtudiant(etudiant);
		etudiant.setListDepots(listDepots);
		depotRepository.save(depot);
		userRepository.save(etudiant);

		return depotRepository.save(depot);
	}
	
	///////////////////////////////////////////// Details Depot /////////////////////////////////////////////

	public Depot getDetailsDepotConvention_de_stageByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Convention_de_stage)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotFiche_de_stageByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Fiche_de_stage)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotBilan_périodique_début_du_stageByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Bilan_périodique_début_du_stage)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotBilan_périodique_milieu_du_stageByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Bilan_périodique_milieu_du_stage)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotBilan_périodique_fin_du_stageByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Bilan_périodique_fin_du_stage)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotRapport_premiere_versionByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Rapport_premiere_version)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotRapport_version_finaleByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Rapport_version_finale)) {
				return depot;
			}
		}
		return null;
	}

	public Depot getDetailsDepotJournal_de_stageByEtudiantId(Long etudiantId) {

		User etudiant = userService.fetchUserById(etudiantId).get();

		List<Depot> listDepot = etudiant.getListDepots();

		for (Depot depot : listDepot) {
			if (depot.getTypeDepot().equals(TypeDepot.Journal_de_stage)) {
				return depot;
			}
		}
		return null;
	} 

}
