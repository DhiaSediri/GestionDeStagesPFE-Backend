package tn.esprit.spring;

import java.util.Collections;

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

import tn.esprit.spring.repositories.UserRepository;

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
		User admin = new User("adminadmin", "mohameddhia.sediri@esprit.tn", encoder.encode("adminadmin"), roles); 	
		userRepository.save(admin);*/
		
		/*Role studentRole = roleRepository.findByName(ERole.Student).get();
		Set<Role> roles= new HashSet<Role>();
		roles.add(studentRole);
		User student = new User("etudiantetudiant", "sediridhia2@gmail.com", encoder.encode("etudiantetudiant"), roles); 	
		userRepository.save(student);*/
		
		/*Role academic_SupervisorRole = roleRepository.findByName(ERole.Academic_Supervisor).get();
		Set<Role> roles= new HashSet<Role>();
		roles.add(academic_SupervisorRole);
		User academic_Supervisor = new User("encadrantencadrant", "sediridhia@gmail.com", encoder.encode("encadrant"), roles); 	
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
	
}
