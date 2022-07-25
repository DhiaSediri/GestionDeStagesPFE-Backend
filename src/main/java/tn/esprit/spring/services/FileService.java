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
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.models.FileData;

@Service
public class FileService {
	
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

	/////////////////////////////////////////////////////// Documents De Stage ///////////////////////////////////////////////////////
	
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
	
	/////////////////////////////////////////////////////// Rendez-Vous De Stage ///////////////////////////////////////////////////////

	// List Files Rendez-Vous De Stage
	public List<FileData> listFilesRendezVousDeStage() {
		try {
			Path root = Paths.get("src//RendezVousDeStage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}
	
	public void saveFileRendezVousDeStage(MultipartFile file) throws FileUploadException {

		// Creer Dossier RendezVousDeStage
		File dossierRendezVousDeStage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\RendezVousDeStage");
		boolean res = dossierRendezVousDeStage.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File RendezVousDeStage
		try {
			Path root = Paths.get("src//RendezVousDeStage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	// Download List Files RendezVousDeStage
	public Resource downloadFilesRendezVousDeStage(String filename) {
		try {
			Path file = Paths.get("src//RendezVousDeStage//").resolve(filename);
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
	
	// Delete File RendezVousDeStage
	public String deleteFileRendezVousDeStage(String fileName) {
		try {
			File file = new File("src//RendezVousDeStage//" + fileName);
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
	
	/////////////////////////////////////////////////////// Fiches De Stage ///////////////////////////////////////////////////////

	// List Files Fiches De Stage
	public List<FileData> listFilesFichesDeStage() {
		try {
			Path root = Paths.get("src//FichesDeStage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}
	
	public void saveFileFichesDeStage(MultipartFile file) throws FileUploadException {

		// Creer Dossier FichesDeStage
		File dossierFichesDeStage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\FichesDeStage");
		boolean res = dossierFichesDeStage.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File FichesDeStage
		try {
			Path root = Paths.get("src//FichesDeStage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	// Download List Files Fiches De Stage
	public Resource downloadFilesFichesDeStage(String filename) {
		try {
			Path file = Paths.get("src//FichesDeStage//").resolve(filename);
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
	
	// Delete File FichesDeStage
	public String deleteFileFichesDeStage(String fileName) {
		try {
			File file = new File("src//FichesDeStage//" + fileName);
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

	/////////////////////////////////////////////////////// Offres De Stage ///////////////////////////////////////////////////////

	// List Files Offres De Stage
	public List<FileData> listFileOffresDeStage(String societe, String session, String option) {

		try {
			Path root = Paths.get(
					/* filesPathOffresDeStage */"src//OffresDeStage-" + societe + "-" + session + "-" + option + "//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}
	
	public void saveFileOffresDeStage(MultipartFile file, String societe, String session, String option)
			throws FileUploadException {

		// Creer Dossier Upload Offres De Stage
		File dossier = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\OffresDeStage-"
						+ societe + "-" + session + "-" + option);
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Offres De Stage
		try {
			Path root = Paths.get("src//OffresDeStage-" + societe + "-" + session + "-" + option + "//");
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
	public String deleteFileOffresDeStage(String fileName, String societe, String session, String option) {
		try {
			File file = new File("src//OffresDeStage-" + societe + "-" + session + "-" + option + "//" + fileName);
			System.out.println(fileName);
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

	/////////////////////////////////////////////////////// Rapports De Stage ///////////////////////////////////////////////////////

	// List Files Rapports De Stage
	public List<FileData> listFileRapportsDeStage(String session, String option, String encadrant) {

		try {
			Path root = Paths.get(/* filesPathRapportsDeStage */"src//RapportsDeStage-" + session + "-" + option + "-"
					+ encadrant + "//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}
	
	public void saveFileRapportsDeStage(MultipartFile file, String session, String option, String encadrant)
			throws FileUploadException {

		// Creer Dossier Upload Rapports De Stage
		File dossier = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\RapportsDeStage-"
						+ session + "-" + option + "-" + encadrant);
		boolean res = dossier.mkdir();

		if (res) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Rapports De Stage
		try {
			Path root = Paths.get("src//RapportsDeStage-" + session + "-" + option + "-" + encadrant + "//");
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
	public String deleteFileRapportsDeStage(String fileName, String session, String option, String encadrant) {
		try {
			File file = new File("src//RapportsDeStage-" + session + "-" + option + "-" + encadrant + "//" + fileName);
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
	
	//////////////////////////////////////////////// List Files Depots //////////////////////////////////////////////////////
	
	// List File Depot Convention_de_stage
	public List<FileData> listDepotConvention_de_stage(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Convention_de_stage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}
	
	// List File Depot Fiche_de_stage
	public List<FileData> listDepotFiche_de_stage(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Fiche_de_stage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// List File Depot Bilan_périodique_début_du_stage
	public List<FileData> listDepotBilan_périodique_début_du_stage(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Bilan_périodique_début_du_stage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// List File Depot Bilan_périodique_milieu_du_stage
	public List<FileData> listDepotBilan_périodique_milieu_du_stage(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Bilan_périodique_milieu_du_stage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// List File Depot Bilan_périodique_fin_du_stage
	public List<FileData> listDepotBilan_périodique_fin_du_stage(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Bilan_périodique_fin_du_stage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// List File Depot Rapport_premiere_version
	public List<FileData> listDepotRapport_premiere_version(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Rapport_premiere_version//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// List File Depot Rapport_version_finale
	public List<FileData> listDepotRapport_version_finale(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Rapport_version_finale//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}

	// List File Depot Journal_de_stage
	public List<FileData> listDepotJournal_de_stage(String emailEtudiant) {
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Journal_de_stage//");

			if (Files.exists(root)) {
				return Files.walk(root, 1).filter(path -> !path.equals(root)).filter(path -> path.toFile().isFile())
						.collect(Collectors.toList()).stream().map(this::pathToFileData).collect(Collectors.toList());
			}

			return Collections.emptyList();
		} catch (IOException e) {
			throw new RuntimeException("Could not list the files!");
		}
	}
	
	//////////////////////////////////////////////// save Files Depots //////////////////////////////////////////////////////

	public void saveFileDepotConvention_de_stage(MultipartFile file, String emailEtudiant) throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Convention_de_stage
		File dossierConvention_de_stage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Convention_de_stage");
		boolean res2 = dossierConvention_de_stage.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Convention_de_stage
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Convention_de_stage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotFiche_de_stage(MultipartFile file, String emailEtudiant) throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Fiche_de_stage
		File dossierFiche_de_stage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Fiche_de_stage");
		boolean res2 = dossierFiche_de_stage.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Fiche_de_stage
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Fiche_de_stage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotBilan_périodique_début_du_stage(MultipartFile file, String emailEtudiant)
			throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Bilan_périodique_début_du_stage
		File dossierBilan_périodique_début_du_stage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Bilan_périodique_début_du_stage");
		boolean res2 = dossierBilan_périodique_début_du_stage.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Bilan_périodique_début_du_stage
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Bilan_périodique_début_du_stage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotBilan_périodique_milieu_du_stage(MultipartFile file, String emailEtudiant)
			throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Bilan_périodique_milieu_du_stage
		File dossierBilan_périodique_milieu_du_stage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Bilan_périodique_milieu_du_stage");
		boolean res2 = dossierBilan_périodique_milieu_du_stage.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Bilan_périodique_milieu_du_stage
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Bilan_périodique_milieu_du_stage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotBilan_périodique_fin_du_stage(MultipartFile file, String emailEtudiant)
			throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Bilan_périodique_fin_du_stage
		File dossierBilan_périodique_fin_du_stage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Bilan_périodique_fin_du_stage");
		boolean res2 = dossierBilan_périodique_fin_du_stage.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Bilan_périodique_fin_du_stage
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Bilan_périodique_fin_du_stage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotRapport_premiere_version(MultipartFile file, String emailEtudiant)
			throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Rapport_premiere_version
		File dossierRapport_premiere_version = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Rapport_premiere_version");
		boolean res2 = dossierRapport_premiere_version.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Rapport_premiere_version
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Rapport_premiere_version//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotRapport_version_finale(MultipartFile file, String emailEtudiant)
			throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Rapport_version_finale
		File dossierRapport_version_finale = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Rapport_version_finale");
		boolean res2 = dossierRapport_version_finale.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Rapport_version_finale
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Rapport_version_finale//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public void saveFileDepotJournal_de_stage(MultipartFile file, String emailEtudiant) throws FileUploadException {

		// Creer Dossier Depot
		File dossierDepot = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant);
		boolean res1 = dossierDepot.mkdir();

		if (res1) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Creer Dossier Journal_de_stage
		File dossierJournal_de_stage = new File(
				"C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\Authentication\\src\\Depot-"
						+ emailEtudiant + "\\Journal_de_stage");
		boolean res2 = dossierJournal_de_stage.mkdir();

		if (res2) {
			System.out.println("Le dossier a été créé.");
		} else {
			System.out.println("Le dossier existe déja.");
		}

		// Upload File Depot Journal_de_stage
		try {
			Path root = Paths.get("src//Depot-" + emailEtudiant + "//Journal_de_stage//");
			Path resolve = root.resolve(file.getOriginalFilename());
			if (resolve.toFile().exists()) {
				throw new FileUploadException("File already exists: " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), resolve);
		} catch (Exception e) {
			throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
		}
	}
	
	/////////////////////////////////////////////////////// Delete Files Depots ///////////////////////////////////////////////////////

	// Delete File Depot Convention_de_stage
	public String deleteFileDepotConvention_de_stage(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Convention_de_stage//" + fileName);
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

	// Delete File Depot Fiche_de_stage
	public String deleteFileDepotFiche_de_stage(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Fiche_de_stage//" + fileName);
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

	// Delete File Depot Bilan_périodique_début_du_stage
	public String deleteFileDepotBilan_périodique_début_du_stage(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Bilan_périodique_début_du_stage//" + fileName);
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

	// Delete File Depot Bilan_périodique_milieu_du_stage
	public String deleteFileDepotBilan_périodique_milieu_du_stage(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Bilan_périodique_milieu_du_stage//" + fileName);
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

	// Delete File Depot Bilan_périodique_fin_du_stage
	public String deleteFileDepotBilan_périodique_fin_du_stage(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Bilan_périodique_fin_du_stage//" + fileName);
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

	// Delete File Depot Rapport_premiere_version
	public String deleteFileDepotRapport_premiere_version(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Rapport_premiere_version//" + fileName);
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

	// Delete File Depot Rapport_version_finale
	public String deleteFileDepotRapport_version_finale(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Rapport_version_finale//" + fileName);
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

	// Delete File Depot Journal_de_stage
	public String deleteFileDepotJournal_de_stage(String fileName, String emailEtudiant) {
		try {
			File file = new File("src//Depot-" + emailEtudiant + "//Journal_de_stage//" + fileName);
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
