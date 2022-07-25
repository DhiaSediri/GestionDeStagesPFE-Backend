package tn.esprit.spring.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.authJwt.JwtUtils;
import tn.esprit.spring.authRequest.LoginRequest;
import tn.esprit.spring.authRequest.SignupRequest;
import tn.esprit.spring.authRequest.SignupRequestEdit;
import tn.esprit.spring.authResponse.JwtResponse;
import tn.esprit.spring.authResponse.MessageResponse;
import tn.esprit.spring.authService.UserDetailsImpl;
import tn.esprit.spring.models.ERole;
import tn.esprit.spring.models.Role;
import tn.esprit.spring.models.User;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.User)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				
				case "Admin":
					Role adminRole = roleRepository.findByName(ERole.Admin)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
					
				case "Student":
					Role studentRole = roleRepository.findByName(ERole.Student)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(studentRole);
					break;
					
				case "Academic_Supervisor":
					Role academicSupervisorRole = roleRepository.findByName(ERole.Academic_Supervisor)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(academicSupervisorRole);

					break;
					
				default:
					Role userRole = roleRepository.findByName(ERole.User)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/editUser")
	public ResponseEntity<?> editUser(@Valid @RequestBody SignupRequestEdit signUpRequestEdit) {
		
		User user = userRepository.getById(signUpRequestEdit.getId());
		
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is not found."));
		}
		
		user.setUsername(signUpRequestEdit.getUsername());
		user.setEmail(signUpRequestEdit.getEmail());
		user.setPassword(encoder.encode(signUpRequestEdit.getPassword()));

		Set<String> strRoles = signUpRequestEdit.getRole();
		Set<Role> roles = new HashSet<>(); 
		
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.User)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				
				case "Admin":
					Role adminRole = roleRepository.findByName(ERole.Admin)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
					
				case "Student":
					Role studentRole = roleRepository.findByName(ERole.Student)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(studentRole);
					break;
					
				case "AcademicSupervisor":
					Role academicSupervisorRole = roleRepository.findByName(ERole.Academic_Supervisor)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(academicSupervisorRole);

					break;
					
				default:
					Role userRole = roleRepository.findByName(ERole.User)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
	}
	
	@PostMapping("/editUserByHimself")
	public ResponseEntity<?> editUserByHimself(@Valid @RequestBody SignupRequestEdit signUpRequestEdit) {
		
		User user = userRepository.getById(signUpRequestEdit.getId());
		
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is not found."));
		}
		
		user.setUsername(signUpRequestEdit.getUsername());
		user.setEmail(signUpRequestEdit.getEmail());
		user.setPassword(encoder.encode(signUpRequestEdit.getPassword()));
		
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
	}
	
}
