package tn.esprit.spring.controllers;

import com.itextpdf.text.DocumentException;

import tn.esprit.spring.models.DocumentsDeStage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/createPDF")
@Controller
public class PDFCreator {

	@Autowired
	SpringTemplateEngine templateEngine;

	//Creer Dossier Documents De Stage
	@RequestMapping("/creerDossierDocumentsDeStage")
	public @ResponseBody DocumentsDeStage creerDossierDocumentsDeStage(@RequestBody DocumentsDeStage documentsDeStage) {

		File dossier = new File("C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\DocumentDeStage-" + documentsDeStage.getEmailEtudiant());
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}
		return documentsDeStage;
	}

	@RequestMapping("/getConventionDeStage")
	public @ResponseBody DocumentsDeStage savePDFConventionDeStage(@RequestBody DocumentsDeStage documentsDeStage)
			throws IOException, DocumentException {
		Context context = new Context();

		context.setVariable("nom_prenomEtudiant", documentsDeStage.getNom_prenomEtudiant());
		context.setVariable("optionEtudiant", documentsDeStage.getOptionEtudiant());
		context.setVariable("nomSociete", documentsDeStage.getNomSociete());
		context.setVariable("adresseSociete", documentsDeStage.getAdresseSociete());
		context.setVariable("telephoneSociete", documentsDeStage.getTelephoneSociete());
		context.setVariable("emailSociete", documentsDeStage.getEmailSociete());
		context.setVariable("encadrantSociete", documentsDeStage.getEncadrantSociete());
		context.setVariable("encadrantAcademique", documentsDeStage.getEncadrantAcademique());

		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

		context.setVariable("dateDebutStage", s.format(documentsDeStage.getDateDebutStage()));
		context.setVariable("dateFinStage", s.format(documentsDeStage.getDateFinStage()));

		Date dateActuelle = new Date();
		context.setVariable("dateActuelle", s.format(dateActuelle));

		String htmlContentToRender = templateEngine.process("pdfConventionDeStage-template", context);
		String xHtml = xhtmlConvert(htmlContentToRender);

		ITextRenderer renderer = new ITextRenderer();

		String baseUrl = FileSystems.getDefault().getPath("src", "main", "resources", "templates").toUri().toURL()
				.toString();
		renderer.setDocumentFromString(xHtml, baseUrl);
		renderer.layout();

		OutputStream outputStream = new FileOutputStream(
				"src//DocumentDeStage-" + documentsDeStage.getEmailEtudiant() + "//ConventionDeStage-" + documentsDeStage.getEmailEtudiant() + ".pdf");
		renderer.createPDF(outputStream);
		outputStream.close();

		return documentsDeStage;
	}

	@RequestMapping("/getDemandeDeStage")
	public @ResponseBody DocumentsDeStage savePDFDemandeDeStage(@RequestBody DocumentsDeStage documentsDeStage)
			throws IOException, DocumentException {
		Context context = new Context();

		context.setVariable("nom_prenomEtudiant", documentsDeStage.getNom_prenomEtudiant());
		context.setVariable("optionEtudiant", documentsDeStage.getOptionEtudiant());
		context.setVariable("nomSociete", documentsDeStage.getNomSociete());
		context.setVariable("adresseSociete", documentsDeStage.getAdresseSociete());
		context.setVariable("telephoneSociete", documentsDeStage.getTelephoneSociete());
		context.setVariable("emailSociete", documentsDeStage.getEmailSociete());
		context.setVariable("encadrantSociete", documentsDeStage.getEncadrantSociete());
		context.setVariable("encadrantAcademique", documentsDeStage.getEncadrantAcademique());

		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

		context.setVariable("dateDebutStage", s.format(documentsDeStage.getDateDebutStage()));
		context.setVariable("dateFinStage", s.format(documentsDeStage.getDateFinStage()));

		Date dateActuelle = new Date();
		context.setVariable("dateActuelle", s.format(dateActuelle));

		String htmlContentToRender = templateEngine.process("pdfDemandeDeStage-template", context);
		String xHtml = xhtmlConvert(htmlContentToRender);

		ITextRenderer renderer = new ITextRenderer();

		String baseUrl = FileSystems.getDefault().getPath("src", "main", "resources", "templates").toUri().toURL()
				.toString();
		renderer.setDocumentFromString(xHtml, baseUrl);
		renderer.layout();

		OutputStream outputStream = new FileOutputStream(
				"src//DocumentDeStage-" + documentsDeStage.getEmailEtudiant() + "//DemandeDeStage-" + documentsDeStage.getEmailEtudiant() + ".pdf");
		renderer.createPDF(outputStream);
		outputStream.close();

		return documentsDeStage;
	}

	@RequestMapping("/getLettreAffectationStage")
	public @ResponseBody DocumentsDeStage savePDFLettreAffectationStage(@RequestBody DocumentsDeStage documentsDeStage)
			throws IOException, DocumentException {
		Context context = new Context();

		context.setVariable("nom_prenomEtudiant", documentsDeStage.getNom_prenomEtudiant());
		context.setVariable("optionEtudiant", documentsDeStage.getOptionEtudiant());
		context.setVariable("nomSociete", documentsDeStage.getNomSociete());
		context.setVariable("adresseSociete", documentsDeStage.getAdresseSociete());
		context.setVariable("telephoneSociete", documentsDeStage.getTelephoneSociete());
		context.setVariable("emailSociete", documentsDeStage.getEmailSociete());
		context.setVariable("encadrantSociete", documentsDeStage.getEncadrantSociete());
		context.setVariable("encadrantAcademique", documentsDeStage.getEncadrantAcademique());

		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

		context.setVariable("dateDebutStage", s.format(documentsDeStage.getDateDebutStage()));
		context.setVariable("dateFinStage", s.format(documentsDeStage.getDateFinStage()));

		Date dateActuelle = new Date();
		context.setVariable("dateActuelle", s.format(dateActuelle));

		String htmlContentToRender = templateEngine.process("pdfLettreAffectationStage-template", context);
		String xHtml = xhtmlConvert(htmlContentToRender);

		ITextRenderer renderer = new ITextRenderer();

		String baseUrl = FileSystems.getDefault().getPath("src", "main", "resources", "templates").toUri().toURL()
				.toString();
		renderer.setDocumentFromString(xHtml, baseUrl);
		renderer.layout();

		OutputStream outputStream = new FileOutputStream(
				"src//DocumentDeStage-" + documentsDeStage.getEmailEtudiant() + "//LettreAffectationStage-" + documentsDeStage.getEmailEtudiant() + ".pdf");
		renderer.createPDF(outputStream);
		outputStream.close();

		return documentsDeStage;
	}

	private String xhtmlConvert(String html) throws UnsupportedEncodingException {
		Tidy tidy = new Tidy();
		tidy.setInputEncoding("UTF-8");
		tidy.setOutputEncoding("UTF-8");
		tidy.setXHTML(true);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		tidy.parseDOM(inputStream, outputStream);
		return outputStream.toString("UTF-8");
	}
}
