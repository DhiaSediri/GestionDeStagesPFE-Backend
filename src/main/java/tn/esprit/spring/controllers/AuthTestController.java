package tn.esprit.spring.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class AuthTestController {
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('User') or hasRole('Admin') or hasRole('Student') or hasRole('Academic_Supervisor')")
	public String userAccess() {
		return "User Content";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminAccess() {
		return "Admin Content";
	}
	
	@GetMapping("/student")
	@PreAuthorize("hasRole('Student')")
	public String studentAccess() {
		return "Student Content";
	}
	
	@GetMapping("/Academic_Supervisor")
	@PreAuthorize("hasRole('Academic_Supervisor')")
	public String academicSupervisorAccess() {
		return "Academic Supervisor Content";
	}

}
