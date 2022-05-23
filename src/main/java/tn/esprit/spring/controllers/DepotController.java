package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.Date;
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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/depot")
public class DepotController {

	@Autowired
	DepotService depotService;

	@GetMapping("/getDepotList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Depot> fetchDepotList() {

		List<Depot> depotList = new ArrayList<Depot>();
		depotList = depotService.fetchDepotList();
		return depotList;
	}

	@GetMapping("/getDepotById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot fetchDepotById(@PathVariable int id) {

		return depotService.fetchDepotById(id).get();
	}

	@DeleteMapping("/deleteDepotById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDepotById(@PathVariable int id) {

		return depotService.deleteDepotById(id);
	}
	
	@PostMapping("/addDepotConvention_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotConvention_de_stage(@RequestBody Depot depot) {
		
		return depotService.saveDepotConvention_de_stage(depot);
	}
	
	@PostMapping("/addDepotFiche_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotFiche_de_stage(@RequestBody Depot depot) {
		
		return depotService.saveDepotFiche_de_stage(depot);
	}
	
	@PostMapping("/addDepotBilan_périodique_début_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotBilan_périodique_début_du_stage(@RequestBody Depot depot) {
		
		return depotService.saveDepotBilan_périodique_début_du_stage(depot);
	}
	
	@PostMapping("/addDepotBilan_périodique_milieu_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotBilan_périodique_milieu_du_stage(@RequestBody Depot depot) {
		
		return depotService.saveDepotBilan_périodique_milieu_du_stage(depot);
	}
	
	@PostMapping("/addDepotBilan_périodique_fin_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotBilan_périodique_fin_du_stage(@RequestBody Depot depot) {
		
		return depotService.saveDepotBilan_périodique_fin_du_stage(depot);
	}
	
	@PostMapping("/addDepotRapport_premiere_version")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotRapport_premiere_version(@RequestBody Depot depot) {
		
		return depotService.saveDepotRapport_premiere_version(depot);
	}
	
	@PostMapping("/addDepotRapport_version_finale")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotRapport_version_finale(@RequestBody Depot depot) {
		
		return depotService.saveDepotRapport_version_finale(depot);
	}
	
	@PostMapping("/addDepotJournal_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Depot saveDepotJournal_de_stage(@RequestBody Depot depot) {
		
		return depotService.saveDepotJournal_de_stage(depot);
	}

}
