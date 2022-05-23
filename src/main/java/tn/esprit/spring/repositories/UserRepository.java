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
	
	//Récupérer l'utilisateur par son email (pour affecter la demande de stage à l'utilisateur)
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

	@Query(value = "SELECT * FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Student' AND u.encadrant_id IS NOT NULL", nativeQuery = true)
	List<User> getListEtudiantsAffectesAEncadrant();
	
	@Query(value = "SELECT * FROM users u, roles r, user_roles ur  WHERE u.id = ur.user_id AND r.id = ur.role_id AND r.name = 'Student' AND u.encadrant_id = id", nativeQuery = true)
	List<User> getListEtudiantsPourEncadrant(@Param("id") Long id);
	
	@Query(value = "SELECT encadrant_id FROM users WHERE username = 'usernameEtudiant'", nativeQuery = true)
	List<User> getIdEncadrant(@Param("usernameEtudiant") String usernameEtudiant);
	
}
