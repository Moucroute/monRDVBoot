package monRDV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.Specialite;

public interface IRepositorySpecialite extends JpaRepository<Specialite,Long>  {
	
	@Query("SELECT s FROM Specialite s JOIN s.praticiens p WHERE p.utilisateur.id = :utilisateurId")
	public List<Specialite> findByUtilisateurId(@Param("utilisateurId") Long utilisateurId);
	
}
