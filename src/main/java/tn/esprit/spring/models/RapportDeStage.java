package tn.esprit.spring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class RapportDeStage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String option;

	@NotBlank
	private String session;

	@NotBlank
	private String encadrant;

	public RapportDeStage() {
	}

	public RapportDeStage(@NotBlank String option, @NotBlank String session,
			@NotBlank String encadrant) {
		super();
		this.option = option;
		this.session = session;
		this.encadrant = encadrant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getEncadrant() {
		return encadrant;
	}

	public void setEncadrant(String encadrant) {
		this.encadrant = encadrant;
	}

}
