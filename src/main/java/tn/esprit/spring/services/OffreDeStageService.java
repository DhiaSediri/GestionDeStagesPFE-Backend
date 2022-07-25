package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.OffreDeStage;
import tn.esprit.spring.repositories.OffreDeStageRepository;

@Service
public class OffreDeStageService {
	
	@Autowired
	OffreDeStageRepository offreDeStageRepository;

	public List<OffreDeStage> fetchOffreDeStageList() {

		return offreDeStageRepository.findAll();
	}

	public OffreDeStage saveOffreDeStage(OffreDeStage offreDeStage) {
	
		return offreDeStageRepository.save(offreDeStage);
	}

	public Optional<OffreDeStage> fetchOffreDeStageById(int id) {

		return offreDeStageRepository.findById(id);
	}

	public String deleteOffreDeStageById(int id) {

		String result;
		try {
			offreDeStageRepository.deleteById(id);
			result = "Offre de Stage successfully deleted";
		} catch (Exception e) {
			result = "Offre de Stage with id " + id + " is not deleted";
		}
		return result;
	}

}
