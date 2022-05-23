package tn.esprit.spring.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.Depot;
import tn.esprit.spring.models.Etat;
import tn.esprit.spring.models.TypeDepot;
import tn.esprit.spring.repositories.DepotRepository;

@Service
public class DepotService {

	@Autowired
	DepotRepository depotRepository;

	public List<Depot> fetchDepotList() {

		return depotRepository.findAll();
	}

	public Optional<Depot> fetchDepotById(int id) {

		return depotRepository.findById(id);
	}

	public String deleteDepotById(int id) {

		String result;
		try {
			depotRepository.deleteById(id);
			result = "Depot successfully deleted";
		} catch (Exception e) {
			result = "Depot with id " + id + " is not deleted";
		}
		return result;
	}

	public Depot saveDepotConvention_de_stage(Depot depot) {
		
		depot.setTypeDepot(TypeDepot.Convention_de_stage);
		depot.setEtatDepot(Etat.DEPOSEE);
		depot.setDateDepot(new Date());

		return depotRepository.save(depot);
	}

	public Depot saveDepotFiche_de_stage(Depot depot) {

		return depotRepository.save(depot);
	}

	public Depot saveDepotBilan_périodique_début_du_stage(Depot depot) {

		return depotRepository.save(depot);
	}

	public Depot saveDepotBilan_périodique_milieu_du_stage(Depot depot) {

		return depotRepository.save(depot);
	}

	public Depot saveDepotBilan_périodique_fin_du_stage(Depot depot) {

		return depotRepository.save(depot);
	}

	public Depot saveDepotRapport_premiere_version(Depot depot) {

		return depotRepository.save(depot);
	}

	public Depot saveDepotRapport_version_finale(Depot depot) {

		return depotRepository.save(depot);
	}

	public Depot saveDepotJournal_de_stage(Depot depot) {

		return depotRepository.save(depot);
	}

}
