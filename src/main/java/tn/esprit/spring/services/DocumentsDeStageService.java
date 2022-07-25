package tn.esprit.spring.services;

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
	
	/*public int testerDureeStage(DocumentsDeStage documentsDeStage) {
	
		int months = MONTHS.between(documentsDeStage.getDateDebutStage(), documentsDeStage.getDateFinStage());
	
	return months;
	}*/

	public List<DocumentsDeStage> fetchListDocumentsDeStageDEPOSEE() {

		return documentsDeStageRepository.getListDocumentsDeStageDEPOSEE();
	}

}
