package tn.esprit.spring.controllers;

import tn.esprit.spring.models.Details;
import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/emailSender")
@Controller
public class EmailSender {

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/getdetails")
    public @ResponseBody Details sendMail(@RequestBody Details details) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name",details.getName());
        model.put("age",details.getAge());
        model.put("country",details.getCountry());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("email-template", context);

        try {
            helper.setTo(details.getEmail());
            helper.setText(html,true);
            helper.setSubject("Test Mail");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return details;
    }
    
    @RequestMapping("/getdetailsAddUser")
    public @ResponseBody User sendMailAddUser(@RequestBody User user) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("username",user.getUsername());
        model.put("password",user.getPassword());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("emailAddUser-template", context);

        try {
            helper.setTo(user.getEmail());
            helper.setText(html,true);
            helper.setSubject("La plateforme de gestion du stage PFE est prête à l'emploi");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return user;
    }
    
    @RequestMapping("/getdetailsDemandeDeStageAcceptee")
    public @ResponseBody DocumentsDeStage sendMailDemandeDeStageAcceptee(@RequestBody DocumentsDeStage demandeDeStage) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("nom_prenomEtudiant",demandeDeStage.getNom_prenomEtudiant());
        model.put("emailEtudiant",demandeDeStage.getEmailEtudiant());
        model.put("optionEtudiant",demandeDeStage.getOptionEtudiant());
        model.put("nomSociete",demandeDeStage.getNomSociete());
        model.put("adresseSociete",demandeDeStage.getAdresseSociete());
        model.put("telephoneSociete",demandeDeStage.getTelephoneSociete());
        model.put("emailSociete",demandeDeStage.getEmailSociete());
        model.put("encadrantSociete",demandeDeStage.getEncadrantSociete());
        model.put("encadrantAcademique",demandeDeStage.getEncadrantAcademique());
        model.put("dateDebutStage",demandeDeStage.getDateDebutStage());
        model.put("dateFinStage",demandeDeStage.getDateFinStage());
        model.put("dateDemande",demandeDeStage.getDateDemande());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("emailDemandeDeStageAcceptee-template", context);

        try {
            helper.setTo(demandeDeStage.getEmailEtudiant());
            helper.setText(html,true);
            helper.setSubject("Validation de la demande de stage");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return demandeDeStage;
    }
    
    @RequestMapping("/getdetailsDemandeDeStageRefusee")
    public @ResponseBody DocumentsDeStage sendMailDemandeDeStageRefusee(@RequestBody DocumentsDeStage demandeDeStage) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("nom_prenomEtudiant",demandeDeStage.getNom_prenomEtudiant());
        model.put("emailEtudiant",demandeDeStage.getEmailEtudiant());
        model.put("optionEtudiant",demandeDeStage.getOptionEtudiant());
        model.put("nomSociete",demandeDeStage.getNomSociete());
        model.put("adresseSociete",demandeDeStage.getAdresseSociete());
        model.put("telephoneSociete",demandeDeStage.getTelephoneSociete());
        model.put("emailSociete",demandeDeStage.getEmailSociete());
        model.put("encadrantSociete",demandeDeStage.getEncadrantSociete());
        model.put("encadrantAcademique",demandeDeStage.getEncadrantAcademique());
        model.put("dateDebutStage",demandeDeStage.getDateDebutStage());
        model.put("dateFinStage",demandeDeStage.getDateFinStage());
        model.put("dateDemande",demandeDeStage.getDateDemande());

        Context context = new Context();
        context.setVariables(model);
        String html = templateEngine.process("emailDemandeDeStageRefusee-template", context);

        try {
            helper.setTo(demandeDeStage.getEmailEtudiant());
            helper.setText(html,true);
            helper.setSubject("Invalidation de la demande de stage");
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);

        return demandeDeStage;
    }

}
