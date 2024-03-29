package tn.esprit.spring.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.repositories.DocumentsDeStageRepository;

@Service
public class DocumentsDeStageService {

	@Autowired
	DocumentsDeStageRepository documentsDeStageRepository;
	
	// Vérifier si la durée du stage est supèrieure ou égale à 6 mois 
	public int verifierDureeDeStage(DocumentsDeStage documentsDeStage) {
			
		Period diff = Period.between(documentsDeStage.getDateDebutStage(),documentsDeStage.getDateFinStage());
		System.out.println("Months : " + diff.getMonths());
		
		return diff.getMonths();
	}

	public List<DocumentsDeStage> fetchDocumentsDeStageList() {

		return documentsDeStageRepository.findAll();
	}

	public DocumentsDeStage saveDocumentsDeStage(DocumentsDeStage documentsDeStage) {
	
		return documentsDeStageRepository.save(documentsDeStage);
	}

	public Optional<DocumentsDeStage> fetchDocumentsDeStageById(int id) {

		return documentsDeStageRepository.findById(id);
	}

	public String deleteDocumentsDeStageById(int id) {

		String result;
		try {
			documentsDeStageRepository.deleteById(id);
			result = "Documents de stage successfully deleted";
		} catch (Exception e) {
			result = "Documents de stage with id " + id + " is not deleted";
		}
		return result;
	}

	public List<DocumentsDeStage> fetchListDocumentsDeStageDEPOSEE() {

		return documentsDeStageRepository.getListDocumentsDeStageDEPOSEE();
	}

}
