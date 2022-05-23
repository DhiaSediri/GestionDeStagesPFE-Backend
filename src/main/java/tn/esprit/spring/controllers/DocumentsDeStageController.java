package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/getDocumentsDeStageList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<DocumentsDeStage> fetchDocumentsDeStageList(){
			
		List<DocumentsDeStage> documentsDeStageList = new ArrayList<DocumentsDeStage>();	
		documentsDeStageList = documentsDeStageService.fetchDocumentsDeStageList();
		return documentsDeStageList;
	}
	
	@PostMapping("/addDocumentsDeStage")
	@CrossOrigin(origins = "http://localhost:4200")
	public DocumentsDeStage saveDocumentsDeStage(@RequestBody DocumentsDeStage documentsDeStage){
		/*
		documentsDeStage.setEtatDemande(Etat.DEPOSEE);
		documentsDeStage.setDateDemande(new Date());
		System.out.println(documentsDeStage.getEmailEtudiant());
		Optional<User> etudiant = userService.fetchUserByEmail(documentsDeStage.getEmailEtudiant());
		System.out.println(etudiant.get().getId());
		documentsDeStage.setEtudiant(etudiant.get());
		return documentsDeStageService.saveDocumentsDeStage(documentsDeStage);
		*/
		
		documentsDeStage.setEtatDemande(Etat.DEPOSEE);
		documentsDeStage.setDateDemande(new Date());
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
	
	@GetMapping("/getListDocumentsDeStageDEPOSEE")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<DocumentsDeStage> fetchListDocumentsDeStageDEPOSEE(){
			
		List<DocumentsDeStage> listDocumentsDeStageDEPOSEE = new ArrayList<DocumentsDeStage>();	
		listDocumentsDeStageDEPOSEE = documentsDeStageService.fetchListDocumentsDeStageDEPOSEE();
		return listDocumentsDeStageDEPOSEE;
	}

}
