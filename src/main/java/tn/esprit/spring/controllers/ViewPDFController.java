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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/viewPDF")
public class ViewPDFController {

	private static final Logger logger = LoggerFactory.getLogger(ViewPDFController.class);

	@RequestMapping(value = "/getPdfDocumentsDeStage/{fileName}/{emailEtudiant}", method = RequestMethod.GET)
	public Object getPdfDocumentsDeStage(@PathVariable("fileName") String fileName, @PathVariable("emailEtudiant") String emailEtudiant) {
		
		logger.info("Service called: getPdf");
		File file;
		switch (fileName) {
		case "1":
			file = new File(
					"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\DocumentDeStage-\\"
							+ emailEtudiant + "\\ConventionDeStage-" + emailEtudiant + ".pdf");
			break;
		case "2":
			file = new File(
					"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\DocumentDeStage-\\"
							+ emailEtudiant + "\\DemandeDeStage-" + emailEtudiant + ".pdf");
			break;
		case "3":
			file = new File(
					"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\DocumentDeStage-\\"
							+ emailEtudiant + "\\LettreAffectationStage-" + emailEtudiant + ".pdf");
			break;
		default:
			file = new File(
					"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\DocumentDeStage-\\"
							+ emailEtudiant + "\\ConventionDeStage-" + emailEtudiant + ".pdf");
			break;
		}
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			return IOUtils.toByteArray(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Service completed: getPdfDocumentsDeStage");
		return null;
	}

}
