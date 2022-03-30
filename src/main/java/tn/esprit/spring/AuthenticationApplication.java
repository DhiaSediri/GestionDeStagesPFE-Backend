package tn.esprit.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import tn.esprit.spring.controllers.UserController;
import tn.esprit.spring.models.ERole;
import tn.esprit.spring.models.Role;
import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.UserRepository;
import tn.esprit.spring.services.UserService;

@SpringBootApplication
public class AuthenticationApplication implements ApplicationRunner {
	
	@Autowired
	tn.esprit.spring.repositories.RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		/*Role adminRole = roleRepository.findByName(ERole.Admin).get();
		Set<Role> roles= new HashSet<Role>();
		roles.add(adminRole);
		User admin = new User("dhia", "mohameddhia.sediri@esprit.tn", encoder.encode("123123123"), roles); 	
		userRepository.save(admin);*/
	}

}