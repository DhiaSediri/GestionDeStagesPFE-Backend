package tn.esprit.spring.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	private int id;
	
	@NotBlank
	@Size(max = 50)
	private String nom_prenomEtudiant;
	
	@NotBlank
	@Email
	private String emailEtudiant;
	
	@NotBlank
	private String optionEtudiant;
	
	@NotBlank
	private String nomSociete;
	
	@NotBlank
	private String adresseSociete;
	
	@NotBlank
	@Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
	private String telephoneSociete;
	
	@NotBlank
	@Email
	private String emailSociete;
	
	@NotBlank
	@Size(max = 50)
	private String encadrantSociete;
	
	@NotBlank
	@Size(max = 50)
	private String encadrantAcademique;
	
	/*@Temporal(TemporalType.DATE)
	private Date dateDebutStage;*/
	private LocalDate dateDebutStage;
	
	/*@Temporal (TemporalType.DATE)
	private Date dateFinStage;*/
	private LocalDate dateFinStage;
	
	private Etat etatDemande;
	
	private LocalDateTime dateDemande;
	
	@OneToOne(mappedBy = "demandeDeStage")
    private User etudiant;

	public DocumentsDeStage() {}

	public DocumentsDeStage(String nom_prenomEtudiant, String emailEtudiant, String optionEtudiant, String nomSociete, String adresseSociete, String telephoneSociete, 
			String emailSociete, String encadrantSociete, String encadrantAcademique, LocalDate dateDebutStage, LocalDate dateFinStage, Etat etatDemande, LocalDateTime dateDemande) {
		this.nom_prenomEtudiant = nom_prenomEtudiant;
		this.emailEtudiant = emailEtudiant;
		this.optionEtudiant = optionEtudiant;
		this.nomSociete = nomSociete;
		this.adresseSociete = adresseSociete;
		this.telephoneSociete = telephoneSociete;
		this.emailSociete = emailSociete;
		this.encadrantSociete = encadrantSociete;
		this.encadrantAcademique = encadrantAcademique;
		this.dateDebutStage = dateDebutStage;
		this.dateFinStage = dateFinStage;
		this.etatDemande = etatDemande;
		this.dateDemande = dateDemande;
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

	public String getEmailEtudiant() {
		return emailEtudiant;
	}

	public void setEmailEtudiant(String emailEtudiant) {
		this.emailEtudiant = emailEtudiant;
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

	public LocalDate getDateDebutStage() {
		return dateDebutStage;
	}

	public void setDateDebutStage(LocalDate dateDebutStage) {
		this.dateDebutStage = dateDebutStage;
	}

	public LocalDate getDateFinStage() {
		return dateFinStage;
	}

	public void setDateFinStage(LocalDate dateFinStage) {
		this.dateFinStage = dateFinStage;
	}

	public Etat getEtatDemande() {
		return etatDemande;
	}

	public void setEtatDemande(Etat etatDemande) {
		this.etatDemande = etatDemande;
	}

	public LocalDateTime getDateDemande() {
		return dateDemande;
	}

	public void setDateDemande(LocalDateTime dateDemande) {
		this.dateDemande = dateDemande;
	}

	public User getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(User etudiant) {
		this.etudiant = etudiant;
	}
	
}
