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

import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.services.DocumentsDeStageService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/documentsDeStage")
public class DocumentsDeStageController {
	
	@Autowired
	DocumentsDeStageService documentsDeStageService;
	
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
			
		return documentsDeStageService.saveDocumentsDeStage(documentsDeStage);
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

}
