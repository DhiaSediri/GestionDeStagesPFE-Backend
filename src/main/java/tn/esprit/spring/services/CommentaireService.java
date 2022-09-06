package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.Commentaire;
import tn.esprit.spring.repositories.CommentaireRepository;

@Service
public class CommentaireService {

	@Autowired
	CommentaireRepository commentaireRepository;
	
	public List<Commentaire> fetchCommentaireList() {

		return commentaireRepository.findAll();
	}

	public Commentaire saveCommentaire(Commentaire commentaire) {
	
		return commentaireRepository.save(commentaire);
	}

	public Optional<Commentaire> fetchCommentaireById(int id) {

		return commentaireRepository.findById(id);
	}

	public String deleteCommentaireById(int id) {

		String result;
		try {
			commentaireRepository.deleteById(id);
			result = "Commentaire successfully deleted";
		} catch (Exception e) {
			result = "Commentaire with id " + id + " is not deleted";
		}
		return result;
	}
}
