package tn.esprit.spring.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.maquette.UploadResponseMessage;
import tn.esprit.spring.models.FileData;
import tn.esprit.spring.services.FileService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/files")
public class FileController {

	private final FileService fileService;

	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	/////////////////////////////////////////////////////// Documents De Stage ///////////////////////////////////////////////////////

	@GetMapping("/getListFileDocumentsDeStage/{foldername:.+}")
	public List<FileData> listDocumentsDeStage(@PathVariable String foldername) {
		return fileService.listFilesDocumentsDeStage(foldername);
	}

	@GetMapping("{filename:.+}/{emailEtudiant:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename, @PathVariable String emailEtudiant)
			throws IOException {
		Resource file = fileService.download(filename, emailEtudiant);
		Path path = file.getFile().toPath();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	/////////////////////////////////////////////////////// Rendez-Vous De Stage ///////////////////////////////////////////////////////

	@GetMapping("/getListFilesRendezVousDeStage")
	public List<FileData> getListFilesRendezVousDeStage() {
		return fileService.listFilesRendezVousDeStage();
	}
	
	@PostMapping("/uploadFileRendezVousDeStage")
	public ResponseEntity<UploadResponseMessage> uploadFileRendezVousDeStage(@RequestParam("file") MultipartFile file) throws FileUploadException {
		fileService.saveFileRendezVousDeStage(file);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@GetMapping("/downloadFilesRendezVousDeStage/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadFilesRendezVousDeStage(@PathVariable String filename) throws IOException {
		Resource file = fileService.downloadFilesRendezVousDeStage(filename);
		Path path = file.getFile().toPath();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@DeleteMapping("/deleteFileRendezVousDeStage/{fileName:.+}")
	public ResponseEntity<String> deleteFileRendezVousDeStage(@PathVariable String fileName) {
		return new ResponseEntity<>(fileService.deleteFileRendezVousDeStage(fileName),HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////// Fiches De Stage ///////////////////////////////////////////////////////

	@GetMapping("/getListFilesFichesDeStage")
	public List<FileData> getListFilesFichesDeStage() {
		return fileService.listFilesFichesDeStage();
	}
	
	@PostMapping("/uploadFileFichesDeStage")
	public ResponseEntity<UploadResponseMessage> uploadFileFichesDeStage(@RequestParam("file") MultipartFile file) throws FileUploadException {
		fileService.saveFileFichesDeStage(file);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@GetMapping("/downloadFilesFichesDeStage/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> downloadFilesFichesDeStage(@PathVariable String filename) throws IOException {
		Resource file = fileService.downloadFilesFichesDeStage(filename);
		Path path = file.getFile().toPath();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@DeleteMapping("/deleteFileFichesDeStage/{fileName:.+}")
	public ResponseEntity<String> deleteFileFichesDeStage(@PathVariable String fileName) {
		return new ResponseEntity<>(fileService.deleteFileFichesDeStage(fileName),HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////// Offres De Stage ///////////////////////////////////////////////////////

	@GetMapping("/getListFileOffresDeStage/{societe:.+}/{session:.+}/{option:.+}")
	public List<FileData> listFileOffresDeStage(@PathVariable String societe, @PathVariable String session,
			@PathVariable String option) {
		return fileService.listFileOffresDeStage(societe, session, option);
	}
	
	@PostMapping("/uploadFileOffresDeStage/{societe:.+}/{session:.+}/{option:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileOffresDeStage(@RequestParam("file") MultipartFile file,
			@PathVariable String societe, @PathVariable String session, @PathVariable String option)
			throws FileUploadException {
		fileService.saveFileOffresDeStage(file, societe, session, option);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}
	
	@DeleteMapping("/deleteFileOffresDeStage/{fileName}/{societe:.+}/{session:.+}/{option:.+}")
	public ResponseEntity<String> deleteFileOffresDeStage(@PathVariable String fileName, @PathVariable String societe,
			@PathVariable String session, @PathVariable String option) {
		return new ResponseEntity<>(fileService.deleteFileOffresDeStage(fileName, societe, session, option),
				HttpStatus.OK);
	}
	
	/////////////////////////////////////////////////////// Rapports De Stage ///////////////////////////////////////////////////////

	@GetMapping("/getListFileRapportsDeStage/{session:.+}/{option:.+}/{encadrant:.+}")
	public List<FileData> listFileRapportsDeStage(@PathVariable String session, @PathVariable String option,
			@PathVariable String encadrant) {
		return fileService.listFileRapportsDeStage(session, option, encadrant);
	}

	@PostMapping("/uploadFileRapportsDeStage/{session:.+}/{option:.+}/{encadrant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileRapportsDeStage(@RequestParam("file") MultipartFile file,
			@PathVariable String session, @PathVariable String option, @PathVariable String encadrant)
			throws FileUploadException {
		fileService.saveFileRapportsDeStage(file, session, option, encadrant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@DeleteMapping("/deleteFileRapportsDeStage/{fileName}/{session:.+}/{option:.+}/{encadrant:.+}")
	public ResponseEntity<String> deleteFileRapportsDeStage(@PathVariable String fileName, @PathVariable String session,
			@PathVariable String option, @PathVariable String encadrant) {
		return new ResponseEntity<>(fileService.deleteFileRapportsDeStage(fileName, session, option, encadrant),
				HttpStatus.OK);
	}
	
	////////////////////////////////////////////////List Files Depots //////////////////////////////////////////////////////

	@GetMapping("/getListFileDepotConvention_de_stage/{emailEtudiant:.+}")
	public List<FileData> listFileDepotConvention_de_stage(@PathVariable String emailEtudiant) {
		return fileService.listDepotConvention_de_stage(emailEtudiant);
	}

	@GetMapping("/getListFileDepotFiche_de_stage/{emailEtudiant:.+}")
	public List<FileData> listFileDepotFiche_de_stage(@PathVariable String emailEtudiant) {
		return fileService.listDepotFiche_de_stage(emailEtudiant);
	}

	@GetMapping("/getListFileDepotBilan_périodique_début_du_stage/{emailEtudiant:.+}")
	public List<FileData> listFileDepotBilan_périodique_début_du_stage(@PathVariable String emailEtudiant) {
		return fileService.listDepotBilan_périodique_début_du_stage(emailEtudiant);
	}

	@GetMapping("/getListFileDepotBilan_périodique_milieu_du_stage/{emailEtudiant:.+}")
	public List<FileData> listFileDepotBilan_périodique_milieu_du_stage(@PathVariable String emailEtudiant) {
		return fileService.listDepotBilan_périodique_milieu_du_stage(emailEtudiant);
	}

	@GetMapping("/getListFileDepotBilan_périodique_fin_du_stage/{emailEtudiant:.+}")
	public List<FileData> listFileDepotBilan_périodique_fin_du_stage(@PathVariable String emailEtudiant) {
		return fileService.listDepotBilan_périodique_fin_du_stage(emailEtudiant);
	}

	@GetMapping("/getListFileDepotRapport_premiere_version/{emailEtudiant:.+}")
	public List<FileData> listFileDepotRapport_premiere_version(@PathVariable String emailEtudiant) {
		return fileService.listDepotRapport_premiere_version(emailEtudiant);
	}

	@GetMapping("/getListFileDepotRapport_version_finale/{emailEtudiant:.+}")
	public List<FileData> listFileDepotRapport_version_finale(@PathVariable String emailEtudiant) {
		return fileService.listDepotRapport_version_finale(emailEtudiant);
	}

	@GetMapping("/getListFileDepotJournal_de_stage/{emailEtudiant:.+}")
	public List<FileData> listFileDepotJournal_de_stage(@PathVariable String emailEtudiant) {
		return fileService.listDepotJournal_de_stage(emailEtudiant);
	}
	
	////////////////////////////////////////////////save Files Depots //////////////////////////////////////////////////////

	@PostMapping("/uploadFileDepotConvention_de_stage/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotConvention_de_stage(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotConvention_de_stage(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotFiche_de_stage/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotFiche_de_stage(@RequestParam("file") MultipartFile file,
			@PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotFiche_de_stage(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotBilan_périodique_début_du_stage/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotBilan_périodique_début_du_stage(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotBilan_périodique_début_du_stage(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotBilan_périodique_milieu_du_stage/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotBilan_périodique_milieu_du_stage(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotBilan_périodique_milieu_du_stage(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotBilan_périodique_fin_du_stage/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotBilan_périodique_fin_du_stage(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotBilan_périodique_fin_du_stage(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotRapport_premiere_version/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotRapport_premiere_version(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotRapport_premiere_version(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotRapport_version_finale/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotRapport_version_finale(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotRapport_version_finale(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}

	@PostMapping("/uploadFileDepotJournal_de_stage/{emailEtudiant:.+}")
	public ResponseEntity<UploadResponseMessage> uploadFileDepotJournal_de_stage(
			@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
		fileService.saveFileDepotJournal_de_stage(file, emailEtudiant);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
	}
	
	/////////////////////////////////////////////////////// Delete Files Depots ///////////////////////////////////////////////////////

	@DeleteMapping("/deleteFileDepotConvention_de_stage/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotConvention_de_stage(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotConvention_de_stage(fileName, emailEtudiant),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotFiche_de_stage/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotFiche_de_stage(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotFiche_de_stage(fileName, emailEtudiant), HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotBilan_périodique_début_du_stage/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotBilan_périodique_début_du_stage(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotBilan_périodique_début_du_stage(fileName, emailEtudiant),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotBilan_périodique_milieu_du_stage/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotBilan_périodique_milieu_du_stage(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(
				fileService.deleteFileDepotBilan_périodique_milieu_du_stage(fileName, emailEtudiant), HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotBilan_périodique_fin_du_stage/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotBilan_périodique_fin_du_stage(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotBilan_périodique_fin_du_stage(fileName, emailEtudiant),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotRapport_premiere_version/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotRapport_premiere_version(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotRapport_premiere_version(fileName, emailEtudiant),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotRapport_version_finale/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotRapport_version_finale(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotRapport_version_finale(fileName, emailEtudiant),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteFileDepotJournal_de_stage/{fileName:.+}/{emailEtudiant:.+}")
	public ResponseEntity<String> deleteFileDepotJournal_de_stage(@PathVariable String fileName,
			@PathVariable String emailEtudiant) {
		return new ResponseEntity<>(fileService.deleteFileDepotJournal_de_stage(fileName, emailEtudiant),
				HttpStatus.OK);
	}

}
