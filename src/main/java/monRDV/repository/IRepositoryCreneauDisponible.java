package monRDV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.CreneauDisponible;

public interface IRepositoryCreneauDisponible extends JpaRepository<CreneauDisponible, Long> {


	@Query("select c from CreneauDisponible c join c.rendezVous.utilisateur cu where cu.id= :id")
	List<CreneauDisponible> findByUtilisateur(@Param("id") Long idUtilisateur);

	
	@Query("select c from CreneauDisponible c where c.praticien.id =: id and c.rendezVous is not null")
	List<CreneauDisponible> findCreneauWithRdv (@Param("id") Long id);

}
