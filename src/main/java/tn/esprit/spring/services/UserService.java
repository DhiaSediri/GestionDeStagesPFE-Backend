package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> fetchListUsers(){
		
		return userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> fetchUserById(Long id) {
		
		return userRepository.findById(id);
	}
	
	public String deleteUserById(Long id) {
		
		String result;
		try {
			userRepository.deleteById(id);		
			result = "User with id " + id + " successfully deleted";
		} catch (Exception e) {
			result = "User with id " + id + " is not deleted";
		}
		return result;
		
	}
	
	public Optional<User> fetchUserByEmail(String email){
		
		return userRepository.findByEmail(email);
	}
	
	/////////////////////////// Affectation /////////////////////////////////
	
	public List<User> fetchListStudents(){	
		return userRepository.getListStudents();
	}
	
	public List<User> fetchListAcademicsSupervisors(){		
		return userRepository.getListAcademicsSupervisors();
	}

	public List<User> fetchListEtudiantsAffectesAEncadrant() {
		return userRepository.getListEtudiantsAffectesAEncadrant();
	}
	
	/////////////////////////// Statistiques ////////////////////////////////
	
	public int getNumberAdmins() {
		return userRepository.getNumberAdmins();
	}
	
	public int getNumberStudents() {
		return userRepository.getNumberStudents();
	}
	
	public int getNumberAcademicsSupervisors() {
		return userRepository.getNumberAcademicsSupervisors();
	}

}
