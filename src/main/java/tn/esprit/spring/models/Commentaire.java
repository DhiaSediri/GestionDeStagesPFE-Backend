package tn.esprit.spring.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String contenu;
	
	private LocalDateTime toOrderDateCommentaire;
	
	private String toDisplayDateCommentaire;
	
	private TypeDepot typeDepot;
	
	@ManyToOne
	private User commentateur;

	public Commentaire() {}

	public Commentaire(String contenu, LocalDateTime toOrderDateCommentaire, String toDisplayDateCommentaire, TypeDepot typeDepot) {
		this.contenu = contenu;
		this.toOrderDateCommentaire = toOrderDateCommentaire;
		this.toDisplayDateCommentaire = toDisplayDateCommentaire;
		this.typeDepot = typeDepot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDateTime getToOrderDateCommentaire() {
		return toOrderDateCommentaire;
	}

	public void setToOrderDateCommentaire(LocalDateTime toOrderDateCommentaire) {
		this.toOrderDateCommentaire = toOrderDateCommentaire;
	}

	public String getToDisplayDateCommentaire() {
		return toDisplayDateCommentaire;
	}

	public void setToDisplayDateCommentaire(String toDisplayDateCommentaire) {
		this.toDisplayDateCommentaire = toDisplayDateCommentaire;
	}

	public TypeDepot getTypeDepot() {
		return typeDepot;
	}

	public void setTypeDepot(TypeDepot typeDepot) {
		this.typeDepot = typeDepot;
	}

	public User getCommentateur() {
		return commentateur;
	}

	public void setCommentateur(User commentateur) {
		this.commentateur = commentateur;
	}

}
