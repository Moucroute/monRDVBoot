package monRDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.Utilisateur;

public interface IRepositoryUtilisateur extends JpaRepository<Utilisateur, Long> {

	@Query("select u from Utilisateur u left join fetch u.patients p where u.id = :id")
	public Utilisateur findWithPatients(@Param("id") Long id);
	
	@Query("select u from Utilisateur u where u.email = :email")
	public Utilisateur findWithEmail(@Param("email") String email);
}
