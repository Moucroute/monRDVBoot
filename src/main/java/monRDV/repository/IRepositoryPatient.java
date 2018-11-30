package monRDV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.Patient;

public interface IRepositoryPatient extends JpaRepository<Patient, Long>{

	@Query("select p from Patient p join p.utilisateur u where u.id = :id")
	List<Patient> findByUtilisateur(@Param("id") Long identifiantUtilisateur);

}
