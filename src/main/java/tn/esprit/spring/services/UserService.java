package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Optional<User> fetchUserById(int id) {
		
		return userRepository.findById(id);
	}
	
	public String deleteUserById(int id) {
		
		String result;
		try {
			userRepository.deleteById(id);		
			result = "User with id " + id + " successfully deleted";
		} catch (Exception e) {
			result = "User with id " + id + " is not deleted";
		}
		return result;
		
	}

}
