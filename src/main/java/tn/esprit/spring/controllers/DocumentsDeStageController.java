package tn.esprit.spring.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.models.Etat;
import tn.esprit.spring.models.User;
import tn.esprit.spring.services.DocumentsDeStageService;
import tn.esprit.spring.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/documentsDeStage")
public class DocumentsDeStageController {
	
	@Autowired
	DocumentsDeStageService documentsDeStageService;
	
	@Autowired
	UserService userService;
	
	// Vérifier si la durée du stage est supèrieure ou égale à 6 mois 
	@PostMapping("/verifierDureeDeStage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Boolean verifierDureeDeStage(@RequestBody DocumentsDeStage documentsDeStage){
		
		Boolean test = false;	
		int dureeDeStage = documentsDeStageService.verifierDureeDeStage(documentsDeStage);
		if(dureeDeStage >= 6) {
			test = true;
		}
		return test;	
	}
	
	@GetMapping("/getDocumentsDeStageListParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<DocumentsDeStage> fetchgetDocumentsDeStageListParRecherche(@PathVariable String mots_cles) {
		
		List<DocumentsDeStage> documentsDeStageList = new ArrayList<DocumentsDeStage>();
		documentsDeStageList = documentsDeStageService.fetchDocumentsDeStageList();
		
		List<DocumentsDeStage> documentsDeStageListParRecherche = new ArrayList<DocumentsDeStage>();
		
		for (DocumentsDeStage documentsDeStage : documentsDeStageList) {
			if(documentsDeStage.getNom_prenomEtudiant().contains(mots_cles) || String.valueOf(documentsDeStage.getDateDemande()).contains(mots_cles)) {	
				documentsDeStageListParRecherche.add(documentsDeStage);
				
				Collections.sort(documentsDeStageListParRecherche, (o1, o2) -> o1.getDateDemande().compareTo(o2.getDateDemande()));
			}	
		}
		return documentsDeStageListParRecherche;
	}
	
	@GetMapping("/getDocumentsDeStageList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<DocumentsDeStage> fetchDocumentsDeStageList(){
			
		List<DocumentsDeStage> documentsDeStageList = new ArrayList<DocumentsDeStage>();	
		documentsDeStageList = documentsDeStageService.fetchDocumentsDeStageList();
		
		Collections.sort(documentsDeStageList, (o1, o2) -> o1.getDateDemande().compareTo(o2.getDateDemande()));
		
		return documentsDeStageList;
	}
	
	@PostMapping("/addDocumentsDeStage")
	@CrossOrigin(origins = "http://localhost:4200")
	public DocumentsDeStage saveDocumentsDeStage(@RequestBody DocumentsDeStage documentsDeStage){
		
		documentsDeStage.setEtatDemande(Etat.DEPOSEE);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		documentsDeStage.setDateDemande(myDateObj);
				
		System.out.println(documentsDeStage.getEmailEtudiant());
		User etudiant = userService.fetchUserByEmail(documentsDeStage.getEmailEtudiant()).get();
		System.out.println(etudiant.getId());
		documentsDeStage.setEtudiant(etudiant);
		DocumentsDeStage demande = documentsDeStageService.saveDocumentsDeStage(documentsDeStage);
		etudiant.setDemandeDeStage(demande);
		userService.saveUser(etudiant);
		return demande;
		
	}

	@GetMapping("/getDocumentsDeStageById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public DocumentsDeStage fetchDocumentsDeStageById(@PathVariable int id){
			
		return documentsDeStageService.fetchDocumentsDeStageById(id).get();
	}
	
	@DeleteMapping("/deleteDocumentsDeStageById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteDocumentsDeStageById(@PathVariable int id) {
		
		return documentsDeStageService.deleteDocumentsDeStageById(id);
	}
	
	///////////////////////////////////////////// Traitement Demande de stage /////////////////////////////////////////////
	
	@GetMapping("/getDocumentsDeStageDEPOSEEListParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<DocumentsDeStage> fetchDocumentsDeStageDEPOSEEListParRecherche(@PathVariable String mots_cles) {
		
		List<DocumentsDeStage> listDocumentsDeStageDEPOSEE = new ArrayList<DocumentsDeStage>();
		listDocumentsDeStageDEPOSEE = documentsDeStageService.fetchListDocumentsDeStageDEPOSEE();
		
		List<DocumentsDeStage> listDocumentsDeStageDEPOSEEParRecherche = new ArrayList<DocumentsDeStage>();
		
		for (DocumentsDeStage documentsDeStage : listDocumentsDeStageDEPOSEE) {
			if(documentsDeStage.getNom_prenomEtudiant().contains(mots_cles) || documentsDeStage.getEmailEtudiant().contains(mots_cles) || documentsDeStage.getEncadrantAcademique().contains(mots_cles)) {	
				listDocumentsDeStageDEPOSEEParRecherche.add(documentsDeStage);
			}	
		}
		return listDocumentsDeStageDEPOSEEParRecherche;
	}
	
	@GetMapping("/getListDocumentsDeStageDEPOSEE")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<DocumentsDeStage> fetchListDocumentsDeStageDEPOSEE(){
			
		List<DocumentsDeStage> listDocumentsDeStageDEPOSEE = new ArrayList<DocumentsDeStage>();	
		listDocumentsDeStageDEPOSEE = documentsDeStageService.fetchListDocumentsDeStageDEPOSEE();
		return listDocumentsDeStageDEPOSEE;
	}
	
	@PostMapping("/accepterDemande")
	@CrossOrigin(origins = "http://localhost:4200")
	public DocumentsDeStage accepterDemande(@RequestBody DocumentsDeStage documentsDeStage){
			
		DocumentsDeStage demande = documentsDeStageService.fetchDocumentsDeStageById(documentsDeStage.getId()).get();
		demande.setEtatDemande(Etat.TRAITEE);
		return documentsDeStageService.saveDocumentsDeStage(demande);
	}
	
	@PostMapping("/refuserDemande")
	@CrossOrigin(origins = "http://localhost:4200")
	public DocumentsDeStage refuserDemande(@RequestBody DocumentsDeStage documentsDeStage){
		
		DocumentsDeStage demande = documentsDeStageService.fetchDocumentsDeStageById(documentsDeStage.getId()).get();
		demande.setEtatDemande(Etat.REFUSEE);
		return documentsDeStageService.saveDocumentsDeStage(demande);
	}

}
