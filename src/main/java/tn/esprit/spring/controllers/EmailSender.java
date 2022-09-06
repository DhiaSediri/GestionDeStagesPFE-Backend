package tn.esprit.spring.controllers;

import tn.esprit.spring.models.Depot;
import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.models.User;
import tn.esprit.spring.services.DepotService;
import tn.esprit.spring.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/emailSender")
@Controller
public class EmailSender {

	@Autowired
	SpringTemplateEngine templateEngine;

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DepotService depotService;

	@RequestMapping("/getdetailsAddUser")
	public @ResponseBody User sendMailAddUser(@RequestBody User user) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", user.getUsername());
		model.put("password", user.getPassword());

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailAddUser-template", context);

		try {
			helper.setTo(user.getEmail());
			helper.setText(html, true);
			helper.setSubject("La plateforme de gestion du stage PFE est prête à l'emploi");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return user;
	}

	@RequestMapping("/getdetailsAddDemandeDeStageToAdmin")
	public @ResponseBody DocumentsDeStage sendMailAddDemandeDeStageToAdmin(@RequestBody DocumentsDeStage demandeDeStage)
			throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("nom_prenomEtudiant", demandeDeStage.getNom_prenomEtudiant());
		model.put("emailEtudiant", demandeDeStage.getEmailEtudiant());
		model.put("optionEtudiant", demandeDeStage.getOptionEtudiant());
		model.put("nomSociete", demandeDeStage.getNomSociete());
		model.put("adresseSociete", demandeDeStage.getAdresseSociete());
		model.put("telephoneSociete", demandeDeStage.getTelephoneSociete());
		model.put("emailSociete", demandeDeStage.getEmailSociete());
		model.put("encadrantSociete", demandeDeStage.getEncadrantSociete());
		model.put("encadrantAcademique", demandeDeStage.getEncadrantAcademique());
		model.put("dateDebutStage", demandeDeStage.getDateDebutStage());
		model.put("dateFinStage", demandeDeStage.getDateFinStage());
		model.put("dateDemande", demandeDeStage.getDateDemande());

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailAddDemandeDeStageToAdmin-template", context);

		try {
			List<User> listAdmins = userService.fetchListAdmins();
			for (User admin : listAdmins) {
				helper.setTo(admin.getEmail());
			}
			helper.setText(html, true);
			helper.setSubject("Une nouvelle demande de stage effectuée");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return demandeDeStage;
	}

	@RequestMapping("/getdetailsDemandeDeStageAcceptee")
	public @ResponseBody DocumentsDeStage sendMailDemandeDeStageAcceptee(@RequestBody DocumentsDeStage demandeDeStage)
			throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("nom_prenomEtudiant", demandeDeStage.getNom_prenomEtudiant());
		model.put("emailEtudiant", demandeDeStage.getEmailEtudiant());
		model.put("optionEtudiant", demandeDeStage.getOptionEtudiant());
		model.put("nomSociete", demandeDeStage.getNomSociete());
		model.put("adresseSociete", demandeDeStage.getAdresseSociete());
		model.put("telephoneSociete", demandeDeStage.getTelephoneSociete());
		model.put("emailSociete", demandeDeStage.getEmailSociete());
		model.put("encadrantSociete", demandeDeStage.getEncadrantSociete());
		model.put("encadrantAcademique", demandeDeStage.getEncadrantAcademique());
		model.put("dateDebutStage", demandeDeStage.getDateDebutStage());
		model.put("dateFinStage", demandeDeStage.getDateFinStage());
		model.put("dateDemande", demandeDeStage.getDateDemande());
		
		Date dateTraitement = new Date();
		model.put("dateTraitement", dateTraitement);

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailDemandeDeStageAcceptee-template", context);

		try {
			helper.setTo(demandeDeStage.getEmailEtudiant());
			helper.setText(html, true);
			helper.setSubject("Validation de la demande de stage");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return demandeDeStage;
	}

	@RequestMapping("/getdetailsDemandeDeStageRefusee")
	public @ResponseBody DocumentsDeStage sendMailDemandeDeStageRefusee(@RequestBody DocumentsDeStage demandeDeStage)
			throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("nom_prenomEtudiant", demandeDeStage.getNom_prenomEtudiant());
		model.put("emailEtudiant", demandeDeStage.getEmailEtudiant());
		model.put("optionEtudiant", demandeDeStage.getOptionEtudiant());
		model.put("nomSociete", demandeDeStage.getNomSociete());
		model.put("adresseSociete", demandeDeStage.getAdresseSociete());
		model.put("telephoneSociete", demandeDeStage.getTelephoneSociete());
		model.put("emailSociete", demandeDeStage.getEmailSociete());
		model.put("encadrantSociete", demandeDeStage.getEncadrantSociete());
		model.put("encadrantAcademique", demandeDeStage.getEncadrantAcademique());
		model.put("dateDebutStage", demandeDeStage.getDateDebutStage());
		model.put("dateFinStage", demandeDeStage.getDateFinStage());
		model.put("dateDemande", demandeDeStage.getDateDemande());
		
		Date dateTraitement = new Date();
		model.put("dateTraitement", dateTraitement);

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailDemandeDeStageRefusee-template", context);

		try {
			helper.setTo(demandeDeStage.getEmailEtudiant());
			helper.setText(html, true);
			helper.setSubject("Invalidation de la demande de stage");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return demandeDeStage;
	}
	
	@RequestMapping("/getdetailsUploadDepotConventionDeStageToAdmin")
	public @ResponseBody Depot sendMailUploadDepotConventionDeStageToAdmin(@RequestBody Depot depot) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("typeDepot", depot.getTypeDepot());
		model.put("etatDepot", depot.getEtatDepot());
		model.put("dateDepot", depot.getToDisplayDateDepot());
		model.put("usernameEtudiant", depot.getEtudiant().getUsername());
		model.put("emailEtudiant", depot.getEtudiant().getEmail());

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailUploadDepotConventionDeStageToAdmin-template", context);

		try {
			List<User> listAdmins = userService.fetchListAdmins();
			for (User admin : listAdmins) {
				helper.setTo(admin.getEmail());
			}
			helper.setText(html, true);
			helper.setSubject(
					"L'étudiant " + depot.getEtudiant().getUsername() + " a déposé " + depot.getTypeDepot());
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return depot;
	}
	
	@GetMapping("/getdetailsDepotConventionAccepteeToStudent/{depot_id}")
	public @ResponseBody Depot sendMailDepotConventionAccepteeToStudent(@PathVariable int depot_id) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		
		Depot depotConvention = depotService.fetchDepotById(depot_id).get();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("typeDepot", depotConvention.getTypeDepot());
		model.put("etatDepot", depotConvention.getEtatDepot());
		model.put("dateDepot", depotConvention.getToDisplayDateDepot());
		
		Date dateTraitement = new Date();
		model.put("dateTraitement", dateTraitement);

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailDepotConventionAccepteeToStudent-template", context);

		try {
			helper.setTo(depotConvention.getEtudiant().getEmail());
			helper.setText(html, true);
			helper.setSubject("Validation de la convention de stage");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return depotConvention;
	}
	
	@GetMapping("/getdetailsDepotConventionRefuseeToStudent/{depot_id}")
	public @ResponseBody Depot sendMailDepotConventionRefuseeToStudent(@PathVariable int depot_id) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		
		Depot depotConvention = depotService.fetchDepotById(depot_id).get();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("typeDepot", depotConvention.getTypeDepot());
		model.put("etatDepot", depotConvention.getEtatDepot());
		model.put("dateDepot", depotConvention.getToDisplayDateDepot());
		
		Date dateTraitement = new Date();
		model.put("dateTraitement", dateTraitement);

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailDepotConventionRefuseeToStudent-template", context);

		try {
			helper.setTo(depotConvention.getEtudiant().getEmail());
			helper.setText(html, true);
			helper.setSubject("Invalidation de la convention de stage");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return depotConvention;
	}
	
	@RequestMapping("/getdetailsUploadDepotToEncadrant")
	public @ResponseBody Depot sendMailUploadDepotToEncadrant(@RequestBody Depot depot) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("typeDepot", depot.getTypeDepot());
		model.put("etatDepot", depot.getEtatDepot());
		model.put("dateDepot", depot.getToDisplayDateDepot());
		model.put("usernameEtudiant", depot.getEtudiant().getUsername());
		model.put("emailEtudiant", depot.getEtudiant().getEmail());

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailUploadDepotToEncadrant-template", context);

		try {
			System.out.println(depot.getEtudiant().getId());	
			Optional<User> etudiant = userService.fetchUserById(depot.getEtudiant().getId());
			String encadrant_email = etudiant.get().getEncadrant().getEmail();
			System.out.println(encadrant_email);
			helper.setTo(encadrant_email);
			helper.setText(html, true);
			helper.setSubject(
					"Votre étudiant " + depot.getEtudiant().getUsername() + " a déposé " + depot.getTypeDepot());
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return depot;
	}

	@RequestMapping("/getdetailsConfirmationTraitementDepotToStudent")
	public @ResponseBody Depot sendMailConfirmationTraitementDepotToStudent(@RequestBody Depot depot) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("typeDepot", depot.getTypeDepot());
		model.put("etatDepot", depot.getEtatDepot());
		model.put("dateDepot", depot.getToDisplayDateDepot());
		model.put("usernameEncadrant", depot.getEtudiant().getEncadrant().getUsername());
		model.put("emailEncadrant", depot.getEtudiant().getEncadrant().getEmail());
		
		Date dateTraitement = new Date();
		model.put("dateTraitement", dateTraitement);

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailConfirmationTraitementDepotToStudent-template", context);

		try {
			helper.setTo(depot.getEtudiant().getEmail());
			helper.setText(html, true);
			helper.setSubject(
					"Votre encadrant " + depot.getEtudiant().getEncadrant().getUsername() + " a traité votre " + depot.getTypeDepot());
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return depot;
	}
	
	@RequestMapping("/getdetailsAffectationToEtudiant/{encadrant_id}/{etudiant_id}")
	public @ResponseBody User sendMailAffectationToEtudiant(@PathVariable Long encadrant_id, @PathVariable Long etudiant_id) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		User encadrant = userService.fetchUserById(encadrant_id).get();	
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", encadrant.getUsername());
		model.put("email", encadrant.getEmail());

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailAffectationToEtudiant-template", context);

		try {
			User etudiant = userService.fetchUserById(etudiant_id).get();
			helper.setTo(etudiant.getEmail());
			helper.setText(html, true);
			helper.setSubject("Affectation d'un encadrant académique à votre stage PFE");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return encadrant;
	}
	
	@RequestMapping("/getdetailsAffectationToEncadrant/{etudiant_id}")
	public @ResponseBody User sendMailAffectationToEncadrant(@PathVariable Long etudiant_id) throws Exception {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		User etudiant = userService.fetchUserById(etudiant_id).get();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", etudiant.getUsername());
		model.put("option", etudiant.getDemandeDeStage().getOptionEtudiant());
		model.put("email", etudiant.getEmail());

		Context context = new Context();
		context.setVariables(model);
		String html = templateEngine.process("emailAffectationToEncadrant-template", context);

		try {
			helper.setTo(etudiant.getEncadrant().getEmail());
			helper.setText(html, true);
			helper.setSubject("Affectation d'un étudiant à votre liste d'encadrement des stage PFE");
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
		sender.send(message);

		return etudiant;
	}

}
