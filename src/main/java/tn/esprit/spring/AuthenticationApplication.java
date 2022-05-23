package tn.esprit.spring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.unit.DataSize;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		
		/*Role studentRole = roleRepository.findByName(ERole.Student).get();
		Set<Role> roles= new HashSet<Role>();
		roles.add(studentRole);
		User student = new User("mohamed", "mohamed.sediri@esprit.tn", encoder.encode("student"), roles); 	
		userRepository.save(student);*/
		
		/*Role academic_SupervisorRole = roleRepository.findByName(ERole.Academic_Supervisor).get();
		Set<Role> roles= new HashSet<Role>();
		roles.add(academic_SupervisorRole);
		User academic_Supervisor = new User("test111111111111", "mohamed111.sediri@esprit.tn", encoder.encode("testtesttest"), roles); 	
		userRepository.save(academic_Supervisor);*/
		
		//test affectation
		/*Role studentRole = roleRepository.findByName(ERole.Student).get();
		Set<Role> roles= new HashSet<Role>();
		roles.add(studentRole);
		User student = new User("studentttttttt", "studenttttttttttt.sediri@esprit.tn", encoder.encode("studenttttttt"), roles); 	
		
		
		Role academic_SupervisorRole = roleRepository.findByName(ERole.Academic_Supervisor).get();
		Set<Role> roless= new HashSet<Role>();
		roless.add(academic_SupervisorRole);
		User academic_Supervisor = new User("encadranttttt", "encadrantttttttttttt.sediri@esprit.tn", encoder.encode("encadrantttttt"), roless); 	
		
		
		userRepository.save(academic_Supervisor);
		
		student.setEncadrant(academic_Supervisor);
		
		userRepository.save(student);
		
		//zeydiiiiiiin
		List<User> listStudents = new ArrayList<User>();
		listStudents.add(student);
		academic_Supervisor.setListStudents(listStudents);
		
		userRepository.save(academic_Supervisor);*/	
	}
	
	//génération PDF
	@Bean
	@SuppressWarnings("unchecked")
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	/*// ajouter pour upload file
	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setMaxFileSize(DataSize.ofKilobytes(512));
	    factory.setMaxRequestSize(DataSize.ofKilobytes(512));
	    return factory.createMultipartConfig();
	}
	// ajouter pour upload file
	@Bean
	WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/api/files/uploadFile").allowedOrigins("http://localhost:4200");
	        }
	    };
	}*/
	
}
