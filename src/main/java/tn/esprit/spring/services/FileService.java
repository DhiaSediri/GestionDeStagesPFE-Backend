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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.models.FileData;

@Service
public class FileService {
	
    @Value("src//")
    private String filesPath;

    public Resource download(String filename) {
        try {
            Path file = Paths.get(filesPath)
                             .resolve(filename);
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

    public List<FileData> list() {
        try {
            Path root = Paths.get(filesPath);

            if (Files.exists(root)) {
                return Files.walk(root, 1)
                            .filter(path -> !path.equals(root))
                            .filter(path -> path.toFile()
                                                .isFile())
                            .collect(Collectors.toList())
                            .stream()
                            .map(this::pathToFileData)
                            .collect(Collectors.toList());
            }

            return Collections.emptyList();
        } catch (IOException e) {
            throw new RuntimeException("Could not list the files!");
        }
    }

    private FileData pathToFileData(Path path) {
        FileData fileData = new FileData();
        String filename = path.getFileName()
                              .toString();
        fileData.setFilename(filename);

        try {
            fileData.setContentType(Files.probeContentType(path));
            fileData.setSize(Files.size(path));
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }

        return fileData;
    }
    
    //upload file
    @Value("${upload.path}")
	 private String uploadPath;

	 public void save(MultipartFile file) throws FileUploadException {
	     try {
	         Path root = Paths.get(uploadPath);
	         Path resolve = root.resolve(file.getOriginalFilename());
	         if (resolve.toFile().exists()) {        	 
	             throw new FileUploadException("File already exists: " + file.getOriginalFilename());
	         }
	         Files.copy(file.getInputStream(), resolve);
	     } catch (Exception e) {
	         throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
	       }
	 }
	 
	 public String deleteFile(String fileName) {
		 try {
	        	File file = new File(uploadPath+fileName);
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
