package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.models.Role;
import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.DepotService;
import tn.esprit.spring.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	DepotService depotService;
	
	@GetMapping("/getUserListParRecherche/{mots_cles}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<User> fetchUserListParRecherche(@PathVariable String mots_cles) {
		
		List<User> userList = new ArrayList<User>();
		userList = userService.fetchListUsers();
		
		List<User> userListParRecherche = new ArrayList<User>();
		
		for (User user : userList) {
			
			for (Role role : user.getRoles()) {
				if(String.valueOf(role.getName()).contains(mots_cles)) {
					userListParRecherche.add(user);
				}
			}
			
			if(user.getUsername().contains(mots_cles)|| user.getEmail().contains(mots_cles)) {	
				userListParRecherche.add(user);
			}	
		}
		
		return userListParRecherche;
	}

	@GetMapping("/getUserList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<User> fetchListUsers() {

		List<User> listUsers = new ArrayList<User>();
		listUsers = userService.fetchListUsers();
		return listUsers;
	}

	@PostMapping("/addUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User saveUser(@RequestBody User user) {

		return userService.saveUser(user);
	}

	@GetMapping("/getUserById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public User fetchUserById(@PathVariable Long id) {

		return userService.fetchUserById(id).get();
	}

	@DeleteMapping("/deleteUserById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteUserById(@PathVariable Long id) {

		return userService.deleteUserById(id);
	}

	/////////////////////////// Affectation /////////////////////////////////

	@GetMapping("/getAcademicsSupervisorList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<User> fetchListAcademicsSupervisors() {

		List<User> listAcademicsSupervisors = new ArrayList<User>();
		listAcademicsSupervisors = userService.fetchListAcademicsSupervisors();
		return listAcademicsSupervisors;
	}

	@GetMapping("/getStudentsList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<User> fetchListStudents() {

		List<User> listStudents = new ArrayList<User>();
		listStudents = userService.fetchListStudents();
		return listStudents;
	}

	@GetMapping("/addAffectation/{encadrant_id}/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void addAffectation(@PathVariable Long encadrant_id, @PathVariable Long etudiant_id) {

		User academic_Supervisor = userService.fetchUserById(encadrant_id).get();
		User student = userService.fetchUserById(etudiant_id).get();

		student.setEncadrant(academic_Supervisor);

		userService.saveUser(student);
		userService.saveUser(academic_Supervisor);
	}

	@GetMapping("/deleteAffectation/{encadrant_id}/{etudiant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public void deleteAffectation(@PathVariable Long encadrant_id, @PathVariable Long etudiant_id) {

		User academic_Supervisor = userService.fetchUserById(encadrant_id).get();
		User student = userService.fetchUserById(etudiant_id).get();

		student.setEncadrant(null);

		userService.saveUser(student);
		userService.saveUser(academic_Supervisor);
	}

	@GetMapping("/getlistEtudiantsAffectesAEncadrant/{encadrant_id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<User> fetchlistEtudiantsAffectesAEncadrant(@PathVariable Long encadrant_id) {

		List<User> listEtudiantsAffectesAEncadrant = new ArrayList<User>();
		listEtudiantsAffectesAEncadrant = userService.fetchListEtudiantsAffectesAEncadrant(encadrant_id);
		return listEtudiantsAffectesAEncadrant;
	}

	/////////////////////////// Statistiques ////////////////////////////////

	@GetMapping("/getNumberAdmins")
	@CrossOrigin(origins = "http://localhost:4200")
	public int getNumberAdmins() {

		return userService.getNumberAdmins();
	}

	@GetMapping("/getNumberStudents")
	@CrossOrigin(origins = "http://localhost:4200")
	public int getNumberStudents() {

		return userService.getNumberStudents();
	}

	@GetMapping("/getNumberAcademicsSupervisors")
	@CrossOrigin(origins = "http://localhost:4200")
	public int getNumberAcademicsSupervisors() {

		return userService.getNumberAcademicsSupervisors();
	}

}
