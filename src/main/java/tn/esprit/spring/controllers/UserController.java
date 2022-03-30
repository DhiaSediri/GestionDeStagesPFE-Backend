package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.models.User;
import tn.esprit.spring.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/addUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User saveUser(@RequestBody User user){
			
		return userService.saveUser(user);
	}
}
