package monRDV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.Patient;
import monRDV.model.RendezVous;

public interface IRepositoryRendezVous extends JpaRepository<RendezVous, Long> {

	@Query("select rdv from RendezVous rdv join rdv.utilisateur u where u.id = :id")
	List<RendezVous> findByUtilisateur(@Param("id") Long identifiantUtilisateur);
	
	
	
}
