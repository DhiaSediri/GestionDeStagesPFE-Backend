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

import tn.esprit.spring.models.RapportDeStage;
import tn.esprit.spring.services.RapportDeStageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rapportDeStage")
public class RapportDeStageController {

	@Autowired
	RapportDeStageService rapportDeStageService;
	
	@GetMapping("/getRapportDeStageListParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<RapportDeStage> fetchRapportDeStageListParRecherche(@PathVariable String mots_cles) {
		
		List<RapportDeStage> rapportDeStageList = new ArrayList<RapportDeStage>();
		rapportDeStageList = rapportDeStageService.fetchRapportDeStageList();
		
		List<RapportDeStage> rapportDeStageListParRecherche = new ArrayList<RapportDeStage>();
		
		for (RapportDeStage rapportDeStage : rapportDeStageList) {
			if(rapportDeStage.getSession().contains(mots_cles) || rapportDeStage.getOption().contains(mots_cles) || rapportDeStage.getEncadrant().contains(mots_cles)) {	
				rapportDeStageListParRecherche.add(rapportDeStage);
			}	
		}
		return rapportDeStageListParRecherche;
	}

	@GetMapping("/getRapportDeStageList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<RapportDeStage> fetchRapportDeStageList() {

		List<RapportDeStage> rapportDeStageList = new ArrayList<RapportDeStage>();
		rapportDeStageList = rapportDeStageService.fetchRapportDeStageList();
		return rapportDeStageList;
	}

	@PostMapping("/addRapportDeStage")
	@CrossOrigin(origins = "http://localhost:4200")
	public RapportDeStage saveRapportDeStage(@RequestBody RapportDeStage rapportDeStage) {

		return rapportDeStageService.saveRapportDeStage(rapportDeStage);
	}

	@GetMapping("/getRapportDeStageById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public RapportDeStage fetchRapportDeStageById(@PathVariable int id) {

		return rapportDeStageService.fetchRapportDeStageById(id).get();
	}

	@DeleteMapping("/deleteRapportDeStageById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteRapportDeStageById(@PathVariable int id) {

		return rapportDeStageService.deleteRapportDeStageById(id);
	}

}
