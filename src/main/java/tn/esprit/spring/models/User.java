package tn.esprit.spring.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	private String username;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
    
	@JsonIgnore
    @ManyToOne
    private User encadrant;
	
	@JsonIgnore
	@OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Depot> listDepots;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "demandeDeStage_id", referencedColumnName = "id")
    private DocumentsDeStage demandeDeStage;
	
	@JsonIgnore
	@OneToMany(mappedBy = "commentateur", cascade = CascadeType.ALL)
    private List<Commentaire> listCommentaires;

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(String username, String email, String password, Set<Role> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User getEncadrant() {
		return encadrant;
	}

	public void setEncadrant(User encadrant) {
		this.encadrant = encadrant;
	}

	public List<Depot> getListDepots() {
		return listDepots;
	}

	public void setListDepots(List<Depot> listDepots) {
		this.listDepots = listDepots;
	}

	public DocumentsDeStage getDemandeDeStage() {
		return demandeDeStage;
	}

	public void setDemandeDeStage(DocumentsDeStage demandeDeStage) {
		this.demandeDeStage = demandeDeStage;
	}

	public List<Commentaire> getListCommentaires() {
		return listCommentaires;
	}

	public void setListCommentaires(List<Commentaire> listCommentaires) {
		this.listCommentaires = listCommentaires;
	}
	
}