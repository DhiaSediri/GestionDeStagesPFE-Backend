package tn.esprit.spring.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Depot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private TypeDepot typeDepot;
	
	@NotBlank
	private Etat etatDepot;
	
	@Temporal (TemporalType.DATE)
	public Date dateDepot;
	
	 @ManyToOne
	 private User etudiant;

	public Depot() {}

	public Depot(TypeDepot typeDepot, Etat etatDepot, Date dateDepot) {
		this.typeDepot = typeDepot;
		this.etatDepot = etatDepot;
		this.dateDepot = dateDepot;
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

	public Date getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	public User getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(User etudiant) {
		this.etudiant = etudiant;
	}

}
