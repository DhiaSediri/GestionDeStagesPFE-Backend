package tn.esprit.spring.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Depot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@NotBlank
	private TypeDepot typeDepot;
	
	//@NotBlank
	private Etat etatDepot;
	
	private LocalDateTime toOrderDateDepot;
	
	private String toDisplayDateDepot;
	
	@ManyToOne
	private User etudiant;
	
	public Depot() {}

	public Depot(TypeDepot typeDepot, Etat etatDepot, LocalDateTime toOrderDateDepot, String toDisplayDateDepot) {
		this.typeDepot = typeDepot;
		this.etatDepot = etatDepot;
		this.toOrderDateDepot = toOrderDateDepot;
		this.toDisplayDateDepot = toDisplayDateDepot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TypeDepot getTypeDepot() {
		return typeDepot;
	}

	public void setTypeDepot(TypeDepot typeDepot) {
		this.typeDepot = typeDepot;
	}

	public Etat getEtatDepot() {
		return etatDepot;
	}

	public void setEtatDepot(Etat etatDepot) {
		this.etatDepot = etatDepot;
	}

	public LocalDateTime getToOrderDateDepot() {
		return toOrderDateDepot;
	}

	public void setToOrderDateDepot(LocalDateTime toOrderDateDepot) {
		this.toOrderDateDepot = toOrderDateDepot;
	}

	public String getToDisplayDateDepot() {
		return toDisplayDateDepot;
	}

	public void setToDisplayDateDepot(String toDisplayDateDepot) {
		this.toDisplayDateDepot = toDisplayDateDepot;
	}

	public User getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(User etudiant) {
		this.etudiant = etudiant;
	}

}
