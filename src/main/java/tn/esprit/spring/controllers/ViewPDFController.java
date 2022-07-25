package tn.esprit.spring.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/viewPDF")
public class ViewPDFController {

	private static final Logger logger = LoggerFactory.getLogger(ViewPDFController.class);

	@RequestMapping(value = "/getPdfDocumentsDeStage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfDocumentsDeStage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\DocumentDeStage-"
						+ emailEtudiant + "\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfDocumentsDeStage");
		return null;
	}

	@RequestMapping(value = "/getPdfConvention_de_stage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfConvention_de_stage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Convention_de_stage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfConvention_de_stage");
		return null;
	}

	@RequestMapping(value = "/getPdfFiche_de_stage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfFiche_de_stage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Fiche_de_stage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfFiche_de_stage");
		return null;
	}

	@RequestMapping(value = "/getPdfBilan_périodique_début_du_stage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfBilan_périodique_début_du_stage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Bilan_périodique_début_du_stage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfBilan_périodique_début_du_stage");
		return null;
	}

	@RequestMapping(value = "/getPdfBilan_périodique_milieu_du_stage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfBilan_périodique_milieu_du_stage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Bilan_périodique_milieu_du_stage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfBilan_périodique_milieu_du_stage");
		return null;
	}

	@RequestMapping(value = "/getPdfBilan_périodique_fin_du_stage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfBilan_périodique_fin_du_stage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Bilan_périodique_fin_du_stage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfBilan_périodique_fin_du_stage");
		return null;
	}

	@RequestMapping(value = "/getPdfRapport_premiere_version/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfRapport_premiere_version(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Rapport_premiere_version\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfRapport_premiere_version");
		return null;
	}

	@RequestMapping(value = "/getPdfRapport_version_finale/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfRapport_version_finale(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Rapport_version_finale\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfRapport_version_finale");
		return null;
	}

	@RequestMapping(value = "/getPdfJournal_de_stage/{fileName:.+}/{emailEtudiant:.+}", method = RequestMethod.GET)
	public Object getPdfJournal_de_stage(@PathVariable("fileName") String fileName,
			@PathVariable("emailEtudiant") String emailEtudiant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Journal_de_stage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfJournal_de_stage");
		return null;
	}
	
	@RequestMapping(value = "/getPdfOffresDeStage/{fileName:.+}/{societe:.+}/{session:.+}/{option:.+}", method = RequestMethod.GET)
	public Object getPdfOffresDeStage(@PathVariable("fileName") String fileName,
			@PathVariable("societe") String societe, @PathVariable("session") String session,
			@PathVariable("option") String option) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\OffresDeStage-"
						+ societe + "-" + session + "-" + option + "\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfOffresDeStage");
		return null;
	}

	@RequestMapping(value = "/getPdfRapportsDeStage/{fileName:.+}/{session:.+}/{option:.+}/{encadrant:.+}", method = RequestMethod.GET)
	public Object getPdfRapportsDeStage(@PathVariable("fileName") String fileName,
			@PathVariable("session") String session, @PathVariable("option") String option,
			@PathVariable("encadrant") String encadrant) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\RapportsDeStage"
						+ session + "-" + option + "-" + encadrant + "\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfRapportsDeStage");
		return null;
	}
	
	@RequestMapping(value = "/getPdfRendezVousDeStage/{fileName:.+}", method = RequestMethod.GET)
	public Object getPdfRendezVousDeStage(@PathVariable("fileName") String fileName) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\RendezVousDeStage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfRendezVousDeStage");
		return null;
	}
	
	@RequestMapping(value = "/getPdfFichesDeStage/{fileName:.+}", method = RequestMethod.GET)
	public Object getPdfFichesDeStage(@PathVariable("fileName") String fileName) {

		logger.info("Service called: getPdf");

		File file = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\FichesDeStage\\" + fileName);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfFichesDeStage");
		return null;
	}

}
