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

import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;
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
	
	@GetMapping("/getUserList")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<User> fetchListUsers(){
			
		List<User> listUsers = new ArrayList<User>();	
		listUsers = userService.fetchListUsers();
		return listUsers;
	}
	
	@PostMapping("/addUser")
	@CrossOrigin(origins = "http://localhost:4200")
	public User saveUser(@RequestBody User user){
			
		return userService.saveUser(user);
	}

	@GetMapping("/getUserById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public User fetchUserById(@PathVariable Long id){
			
		return userService.fetchUserById(id).get();
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public String deleteUserById(@PathVariable Long id) {
		
		return userService.deleteUserById(id);
	}
	
}
