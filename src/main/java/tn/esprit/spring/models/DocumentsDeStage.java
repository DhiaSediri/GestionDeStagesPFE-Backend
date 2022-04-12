package tn.esprit.spring.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class DocumentsDeStage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@NotBlank
	@Size(max = 20)
	public String nom_prenomEtudiant;
	
	@NotBlank
	@Size(max = 20)
	public String optionEtudiant;
	
	@NotBlank
	@Size(max = 20)	
	public String nomSociete;
	
	@NotBlank
	@Size(max = 30)	
	public String adresseSociete;
	
	@NotBlank
	@Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
	public String telephoneSociete;
	
	@NotBlank
	@Size(max = 50)
	@Email
	public String emailSociete;
	
	@NotBlank
	@Size(max = 20)
	public String encadrantSociete;
	
	@NotBlank
	@Size(max = 20)
	public String encadrantAcademique;
	
	@Temporal(TemporalType.DATE)
	public Date dateDebutStage;
	
	@Temporal (TemporalType.DATE)
	public Date dateFinStage;

	public DocumentsDeStage() {}

	public DocumentsDeStage(String nom_prenomEtudiant, String optionEtudiant, String nomSociete, String adresseSociete, String telephoneSociete, 
			String emailSociete, String encadrantSociete, String encadrantAcademique, Date dateDebutStage, Date dateFinStage) {
		this.nom_prenomEtudiant = nom_prenomEtudiant;
		this.optionEtudiant = optionEtudiant;
		this.nomSociete = nomSociete;
		this.adresseSociete = adresseSociete;
		this.telephoneSociete = telephoneSociete;
		this.emailSociete = emailSociete;
		this.encadrantSociete = encadrantSociete;
		this.encadrantAcademique = encadrantAcademique;
		this.dateDebutStage = dateDebutStage;
		this.dateFinStage = dateFinStage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_prenomEtudiant() {
		return nom_prenomEtudiant;
	}

	public void setNom_prenomEtudiant(String nom_prenomEtudiant) {
		this.nom_prenomEtudiant = nom_prenomEtudiant;
	}

	public String getOptionEtudiant() {
		return optionEtudiant;
	}

	public void setOptionEtudiant(String optionEtudiant) {
		this.optionEtudiant = optionEtudiant;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}

	public String getAdresseSociete() {
		return adresseSociete;
	}

	public void setAdresseSociete(String adresseSociete) {
		this.adresseSociete = adresseSociete;
	}

	public String getTelephoneSociete() {
		return telephoneSociete;
	}

	public void setTelephoneSociete(String telephoneSociete) {
		this.telephoneSociete = telephoneSociete;
	}

	public String getEmailSociete() {
		return emailSociete;
	}

	public void setEmailSociete(String emailSociete) {
		this.emailSociete = emailSociete;
	}

	public String getEncadrantSociete() {
		return encadrantSociete;
	}

	public void setEncadrantSociete(String encadrantSociete) {
		this.encadrantSociete = encadrantSociete;
	}

	public String getEncadrantAcademique() {
		return encadrantAcademique;
	}

	public void setEncadrantAcademique(String encadrantAcademique) {
		this.encadrantAcademique = encadrantAcademique;
	}

	public Date getDateDebutStage() {
		return dateDebutStage;
	}

	public void setDateDebutStage(Date dateDebutStage) {
		this.dateDebutStage = dateDebutStage;
	}

	public Date getDateFinStage() {
		return dateFinStage;
	}

	public void setDateFinStage(Date dateFinStage) {
		this.dateFinStage = dateFinStage;
	}
	
}
