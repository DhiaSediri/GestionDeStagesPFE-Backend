package tn.esprit.spring.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.models.Depot;
import tn.esprit.spring.models.DocumentsDeStage;
import tn.esprit.spring.models.FileData;

@Service
public class FileService {

	/*
	 * @Value("src//") private String filesPath;
	 */

	// List Files Documents De Stage
	public List<FileData> listFilesDocumentsDeStage(String foldername) {
		try {
			Path root = Paths.get("src//DocumentDeStage-" + foldername + "//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// Download List Files Documents De Stage
	public Resource download(String filename, String emailEtudiant) {
		try {
			Path file = Paths.get("src//DocumentDeStage-" + emailEtudiant + "//").resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	/*
	 * @Value("${uploadFileOffresDeStage.path}") private String
	 * filesPathOffresDeStage;
	 */

	// List Files Offres De Stage
	public List<FileData> listFileOffresDeStage() {

		try {
			Path root = Paths.get(/* filesPathOffresDeStage */"src//UploadOffresDeStage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	/*
	 * @Value("${uploadFileRapportsDeStage.path}") private String
	 * filesPathRapportsDeStage;
	 */

	// List Files Rapports De Stage
	public List<FileData> listFileRapportsDeStage() {

		try {
			Path root = Paths.get(/* filesPathRapportsDeStage */"src//UploadRapportsDeStage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	private FileData pathToFileData(Path path) {
		FileData fileData = new FileData();
		String filename = path.getFileName().toString();
		fileData.setFilename(filename);

		try {
			fileData.setContentType(Files.probeContentType(path));
			fileData.setSize(Files.size(path));
		} catch (IOException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}

		return fileData;
	}

	/*
	 * @Value("${uploadFileOffresDeStage.path}") private String
	 * uploadPathFileOffresDeStage;
	 */

	public void saveFileOffresDeStage(MultipartFile file) throws FileUploadException {

		// Creer Dossier Upload Offres De Stage
		File dossier = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\UploadOffresDeStage");
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Offres De Stage
		try {
			Path root = Paths.get("src//UploadOffresDeStage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	// Delete File Offres De Stage
	public String deleteFileOffresDeStage(String fileName) {
		try {
			File file = new File("src//UploadOffresDeStage//" + fileName);
			if (file.delete()) {
				System.out.println(fileName + " is deleted!");
				return fileName + " removed ...";
			} else {
				System.out.println("Sorry, unable to delete the file.");
				return "Sorry, unable to delete the file " + fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	/*
	 * @Value("${uploadFileRapportsDeStage.path}") private String
	 * uploadPathFileRapportsDeStage;
	 */

	public void saveFileRapportsDeStage(MultipartFile file) throws FileUploadException {

		// Creer Dossier Upload Rapports De Stage
		File dossier = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\UploadRapportsDeStage");
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Rapports De Stage
		try {
			Path root = Paths.get("src//UploadRapportsDeStage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	// Delete File Rapports De Stage
	public String deleteFileRapportsDeStage(String fileName) {
		try {
			File file = new File("src//UploadRapportsDeStage//" + fileName);
			if (file.delete()) {
				System.out.println(fileName + " is deleted!");
				return fileName + " removed ...";
			} else {
				System.out.println("Sorry, unable to delete the file.");
				return "Sorry, unable to delete the file " + fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

	/*// Creer Dossier Depot Etudiant
	@RequestMapping("/creerDossierDepotEtudiant")
	public @ResponseBody Depot creerDossierDepotEtudiant(@RequestBody Depot depotEtudiant) {

		File dossier = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ depotEtudiant.getEtudiant().getEmail());
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}
		return depotEtudiant;
	}*/

	// List Files Depots d'un Etudiant
	public List<FileData> listDepotsEtudiant(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	
	public void saveFileDepot(MultipartFile file, String emailEtudiant) throws FileUploadException {

		// Creer Dossier Depot Etudiant
		File dossier = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot
		try {
			Path root = Paths.get("src//Depot-//" + emailEtudiant + "//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	// Delete File Depot
	public String deleteFileDepot(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//" + fileName);
			if (file.delete()) {
				System.out.println(fileName + " is deleted!");
				return fileName + " removed ...";
			} else {
				System.out.println("Sorry, unable to delete the file.");
				return "Sorry, unable to delete the file " + fileName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}

}
