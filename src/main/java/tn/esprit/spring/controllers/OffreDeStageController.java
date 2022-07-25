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

import tn.esprit.spring.models.OffreDeStage;
import tn.esprit.spring.services.OffreDeStageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offreDeStage")
public class OffreDeStageController {

	@Autowired
	OffreDeStageService offreDeStageService;
	
	@GetMapping("/getOffreDeStageListParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<OffreDeStage> fetchOffreDeStageListParRecherche(@PathVariable String mots_cles) {
		
		List<OffreDeStage> offreDeStageList = new ArrayList<OffreDeStage>();
		offreDeStageList = offreDeStageService.fetchOffreDeStageList();
		
		List<OffreDeStage> offreDeStageListParRecherche = new ArrayList<OffreDeStage>();
		
		for (OffreDeStage offreDeStage : offreDeStageList) {
			if(offreDeStage.getSession().contains(mots_cles) || offreDeStage.getOption().contains(mots_cles) || offreDeStage.getSociete().contains(mots_cles)) {	
				offreDeStageListParRecherche.add(offreDeStage);
			}	
		}
		return offreDeStageListParRecherche;
	}

	@GetMapping("/getOffreDeStageList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<OffreDeStage> fetchOffreDeStageList() {

		List<OffreDeStage> offreDeStageList = new ArrayList<OffreDeStage>();
		offreDeStageList = offreDeStageService.fetchOffreDeStageList();
		return offreDeStageList;
	}

	@PostMapping("/addOffreDeStage")
	@CrossOrigin(origins = "http://localhost:4200")
	public OffreDeStage saveOffreDeStage(@RequestBody OffreDeStage offreDeStage) {

		return offreDeStageService.saveOffreDeStage(offreDeStage);
	}

	@GetMapping("/getOffreDeStageById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public OffreDeStage fetchOffreDeStageById(@PathVariable int id) {

		return offreDeStageService.fetchOffreDeStageById(id).get();
	}

	@DeleteMapping("/deleteOffreDeStageById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteRapportDeStageById(@PathVariable int id) {

		return offreDeStageService.deleteOffreDeStageById(id);
	}

}
