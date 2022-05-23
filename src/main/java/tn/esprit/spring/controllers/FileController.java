package tn.esprit.spring.controllers;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.maquette.UploadResponseMessage;
import tn.esprit.spring.models.DocumentsDeStage;
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

    @GetMapping("/getListFileDocumentsDeStage/{foldername:.+}")
    public List<FileData> listDocumentsDeStage(@PathVariable String foldername) {
        return fileService.listFilesDocumentsDeStage(foldername);
    }
    
    /*@RequestMapping("/creerDossierUploadOffresDeStage")
	public void creerDossierUploadOffresDeDtage() {

		File dossier = new File("C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\UploadOffresDeStage");
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}
	}
    
    @RequestMapping("/creerDossierUploadRapportsDeStage")
	public void creerDossierUploadRapportsDeDtage() {

		File dossier = new File("C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\UploadRapportsDeStage");
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}
	}*/
    
    @GetMapping("/getListFileOffresDeStage")
    public List<FileData> listFileOffresDeStage() {
        return fileService.listFileOffresDeStage();
    }
    
    @GetMapping("/getListFileRapportsDeStage")
    public List<FileData> listFileRapportsDeStage() {
        return fileService.listFileRapportsDeStage();
    }

    @GetMapping("{filename:.+}/{emailEtudiant:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, @PathVariable String emailEtudiant) throws IOException {
        Resource file = fileService.download(filename, emailEtudiant);
        Path path = file.getFile()
                        .toPath();

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                             .body(file);
    }
       
    @PostMapping("/uploadFileOffresDeStage")
    public ResponseEntity<UploadResponseMessage> uploadFileOffresDeStage(@RequestParam("file") MultipartFile file) throws FileUploadException {
			fileService.saveFileOffresDeStage(file);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
    
    @PostMapping("/uploadFileRapportsDeStage")
    public ResponseEntity<UploadResponseMessage> uploadFileRapportsDeStage(@RequestParam("file") MultipartFile file) throws FileUploadException {
			fileService.saveFileRapportsDeStage(file);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
    
    @DeleteMapping("/deleteFileOffresDeStage/{fileName}")
    public ResponseEntity<String> deleteFileOffresDeStage(@PathVariable String fileName) {
        return new ResponseEntity<>(fileService.deleteFileOffresDeStage(fileName), HttpStatus.OK); 
    }
    
    @DeleteMapping("/deleteFileRapportsDeStage/{fileName}")
    public ResponseEntity<String> deleteFileRapportsDeStage(@PathVariable String fileName) {
        return new ResponseEntity<>(fileService.deleteFileRapportsDeStage(fileName), HttpStatus.OK); 
    }
    
    @GetMapping("/getListFileDepotEtudiant/{emailEtudiant:.+}")
    public List<FileData> listFileDepotEtudiant(@PathVariable String emailEtudiant) {
        return fileService.listDepotsEtudiant(emailEtudiant);
    }
    
    @PostMapping("/uploadFileDepot")
    public ResponseEntity<UploadResponseMessage> uploadFileDepot(@RequestParam("file") MultipartFile file, @PathVariable String emailEtudiant) throws FileUploadException {
			fileService.saveFileDepot(file, emailEtudiant);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
    
    @DeleteMapping("/deleteFileDepot/{filename:.+}/{emailEtudiant:.+}")
    public ResponseEntity<String> deleteFileDepot(@PathVariable String fileName, @PathVariable String emailEtudiant) {
        return new ResponseEntity<>(fileService.deleteFileDepot(fileName, emailEtudiant), HttpStatus.OK); 
    }
    
}
