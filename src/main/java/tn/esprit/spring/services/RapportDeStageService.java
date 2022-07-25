package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.RapportDeStage;
import tn.esprit.spring.repositories.RapportDeStageRepository;

@Service
public class RapportDeStageService {
	
	@Autowired
	RapportDeStageRepository rapportDeStageRepository;

	public List<RapportDeStage> fetchRapportDeStageList() {

		return rapportDeStageRepository.findAll();
	}

	public RapportDeStage saveRapportDeStage(RapportDeStage rapportDeStage) {
	
		return rapportDeStageRepository.save(rapportDeStage);
	}

	public Optional<RapportDeStage> fetchRapportDeStageById(int id) {

		return rapportDeStageRepository.findById(id);
	}

	public String deleteRapportDeStageById(int id) {

		String result;
		try {
			rapportDeStageRepository.deleteById(id);
			result = "Rapport de stage successfully deleted";
		} catch (Exception e) {
			result = "Rapport de stage with id " + id + " is not deleted";
		}
		return result;
	}

}
