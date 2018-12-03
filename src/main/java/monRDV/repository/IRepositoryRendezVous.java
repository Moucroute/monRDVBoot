package monRDV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.RendezVous;

public interface IRepositoryRendezVous extends JpaRepository<RendezVous, Long> {

	@Query("select rdv from RendezVous rdv join rdv.utilisateur u where u.id = :id")
	List<RendezVous> findByUtilisateur(@Param("id") Long identifiantUtilisateur);
		
	@Query("select r from RendezVous r where r.modalite.id =: id")
	List<RendezVous> findModaliteWithMotif (@Param("id") Long id);
	
	@Query("select distinct r from RendezVous r left join fetch r.creneaux c")
	List<RendezVous> findAllWithCreneaux ();
	
	@Query("select distinct r from RendezVous r left join fetch r.creneaux c where c.praticien.id = :id")
	List<RendezVous> findAllWithCreneauxByPraticien (@Param("id") Long id);
}
