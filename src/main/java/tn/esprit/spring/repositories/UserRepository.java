package tn.esprit.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
	
	//Récupérer l'utilisateur (l'étudiant) par son email pour lui affecter la demande de stage
	@Query(value = "SELECT * FROM users u WHERE email = :email", nativeQuery = true)
	Optional<User> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Admin'", nativeQuery = true)
	public List<User> getListAdmins();
	
	@Query(value = "SELECT * FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Student'", nativeQuery = true)
	public List<User> getListStudents();
	
	@Query(value = "SELECT * FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Academic_Supervisor'", nativeQuery = true)
	public List<User> getListAcademicsSupervisors();
	
	@Query(value = "SELECT COUNT(*) FROM FROM users", nativeQuery = true)
	public int getNumberUsers();	
	
	@Query(value = "SELECT COUNT(*) FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Admin'", nativeQuery = true)
	public int getNumberAdmins();
	
	@Query(value = "SELECT COUNT(*) FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Student'", nativeQuery = true)
	public int getNumberStudents();
	
	@Query(value = "SELECT COUNT(*) FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Academic_Supervisor'", nativeQuery = true)
	public int getNumberAcademicsSupervisors();

	@Query(value = "SELECT * FROM users u WHERE u.encadrant_id = encadrant_id", nativeQuery = true)
	List<User> getListEtudiantsAffectesAEncadrant(@Param("encadrant_id") Long encadrant_id);
	
	//Récupérer l'utilisateur (étudiant et encadrant) par username pour envoyer un mail contenant les détailles de l'affectaion
	@Query(value = "SELECT * FROM users u WHERE username = :username", nativeQuery = true)
	Optional<User> findUserByUsername(@Param("username") String username);
	
}
