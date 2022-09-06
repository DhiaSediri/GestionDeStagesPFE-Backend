package tn.esprit.spring.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import tn.esprit.spring.models.Commentaire;
import tn.esprit.spring.models.TypeDepot;
import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.CommentaireService;
import tn.esprit.spring.services.DepotService;
import tn.esprit.spring.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/commentaire")
public class CommentaireController {
	
	@Autowired
	CommentaireService commentaireService;
	
	@Autowired
	DepotService depotService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	////////////////////////////////////// Récuperer la liste des Commentaires de chaque Dépot //////////////////////////////////////
	
	@GetMapping("/getCommentaireFiche_de_stageList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListCommentaireFiche_de_stage(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
								
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Fiche_de_stage)) {
			commentaireRecupereList.add(commentaire);
			
			 Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}
		
		return commentaireRecupereList;
	}
	
	@GetMapping("/getCommentaireBilan_periodique_debut_du_stageList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListCommentaireBilan_periodique_debut_du_stage(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
						
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Bilan_périodique_début_du_stage)) {
			commentaireRecupereList.add(commentaire); 
			
			Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}	
		return commentaireRecupereList;
	}
	
	@GetMapping("/getCommentaireBilan_periodique_milieu_du_stageList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListCommentaireBilan_periodique_milieu_du_stage(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
						
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Bilan_périodique_milieu_du_stage)) {
			commentaireRecupereList.add(commentaire); 
			
			Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}	
		return commentaireRecupereList;
	}
	
	@GetMapping("/getCommentaireBilan_periodique_fin_du_stageList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListCommentaireBilan_periodique_fin_du_stage(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
						
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Bilan_périodique_fin_du_stage)) {
			commentaireRecupereList.add(commentaire); 
			
			Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}	
		return commentaireRecupereList;
	}
	
	@GetMapping("/getCommentaireRapport_premiere_versionList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListCommentaireRapport_premiere_version(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
						
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Rapport_premiere_version)) {
			commentaireRecupereList.add(commentaire); 
			
			Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}	
		return commentaireRecupereList;
	}
	
	@GetMapping("/getCommentaireRapport_version_finaleList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListCommentaireRapport_version_finale(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
						
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Rapport_version_finale)) {
			commentaireRecupereList.add(commentaire);
			
			Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}	
		return commentaireRecupereList;
	}
	
	@GetMapping("/getCommentaireJournal_de_stageList/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchListJournal_de_stage(@PathVariable Long userId){
		
		Long encadrantId = (long) 0;
		
		User test = userService.fetchUserById(userId).get();
		if (test.getEncadrant() != null) {
			encadrantId = test.getEncadrant().getId();
		}
		
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();
		commentaireList = commentaireService.fetchCommentaireList();
		
		List<Commentaire> commentaireRecupereList = new ArrayList<Commentaire>();
						
		for (Commentaire commentaire : commentaireList) {
			if((commentaire.getCommentateur().getId() == userId || commentaire.getCommentateur().getId() == encadrantId) && commentaire.getTypeDepot().equals(TypeDepot.Journal_de_stage)) {
			commentaireRecupereList.add(commentaire); 
			
			Collections.sort(commentaireRecupereList, (o1, o2) -> o1.getToOrderDateCommentaire().compareTo(o2.getToOrderDateCommentaire()));
			}
		}	
		return commentaireRecupereList;
	}
	
	////////////////////////////////////////////////// CRUD Commentaire //////////////////////////////////////////////////
	
	@PostMapping("/addCommentaireFiche_de_stage/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireFiche_de_stage(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Fiche_de_stage);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/addCommentaireBilan_periodique_debut_du_stage/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireBilan_periodique_debut_du_stage(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Bilan_périodique_début_du_stage);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/addCommentaireBilan_periodique_milieu_du_stage/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireBilan_periodique_milieu_du_stage(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Bilan_périodique_milieu_du_stage);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/addCommentaireBilan_periodique_fin_du_stage/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireBilan_periodique_fin_du_stage(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Bilan_périodique_fin_du_stage);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/addCommentaireRapport_premiere_version/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireRapport_premiere_version(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Rapport_premiere_version);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/addCommentaireRapport_version_finale/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireRapport_version_finale(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Rapport_version_finale);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/addCommentaireJournal_de_stage/{userId}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire saveCommentaireJournal_de_stage(@PathVariable Long userId, @RequestBody String contenu){
		
		Commentaire commentaire = new Commentaire();
		commentaire.setContenu(contenu);
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Journal_de_stage);
		
		User commentateur = userRepository.findById(userId).get();
		commentaire.setCommentateur(commentateur);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/editCommentaireFiche_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire editCommentaireFiche_de_stage(@RequestBody Commentaire commentaire){
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Fiche_de_stage);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/editCommentaireBilan_periodique_debut_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire editCommentaireBilan_periodique_debut_du_stage(@RequestBody Commentaire commentaire){
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Bilan_périodique_début_du_stage);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/editCommentaireBilan_periodique_milieu_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire editCommentaireBilan_periodique_milieu_du_stage(@RequestBody Commentaire commentaire){
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Bilan_périodique_milieu_du_stage);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/editCommentaireBilan_periodique_fin_du_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire editCommentaireBilan_periodique_fin_du_stage(@RequestBody Commentaire commentaire){
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Bilan_périodique_fin_du_stage);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/editCommentaireRapport_premiere_version")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire editCommentaireRapport_premiere_version(@RequestBody Commentaire commentaire){
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Rapport_premiere_version);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}
	
	@PostMapping("/editCommentaireJournal_de_stage")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire editCommentaireJournal_de_stage(@RequestBody Commentaire commentaire){
		
		
		LocalDateTime myDateObj = LocalDateTime.now();
		commentaire.setToOrderDateCommentaire(myDateObj);
		
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);
		commentaire.setToDisplayDateCommentaire(formattedDate);
		
		commentaire.setTypeDepot(TypeDepot.Journal_de_stage);
		
		commentaireService.saveCommentaire(commentaire);
		
		return commentaire;
	}

	@GetMapping("/getCommentaireById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public Commentaire fetchCommentaireById(@PathVariable int id){
			
		return commentaireService.fetchCommentaireById(id).get();
	}
	
	@DeleteMapping("/deleteCommentaireById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteCommentaireById(@PathVariable int id) {
		
		return commentaireService.deleteCommentaireById(id);
	}
	
	@GetMapping("/getCommentaireList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Commentaire> fetchCommentaireList(){
			
		List<Commentaire> commentaireList = new ArrayList<Commentaire>();	
		commentaireList = commentaireService.fetchCommentaireList();
		return commentaireList;
	}

}
