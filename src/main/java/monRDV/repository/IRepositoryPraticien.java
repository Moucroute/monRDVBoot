package monRDV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monRDV.model.Praticien;

public interface IRepositoryPraticien extends JpaRepository<Praticien, Long>{
	
	@Query("select p from Praticien p left join fetch p.lieux l where l.adresse.ville = :ville")
	List<Praticien> findByVille(@Param("ville") String ville);
	
	
	@Query("select p from Praticien p left join fetch p.lieux l left join fetch p.creneaux c where l.adresse.ville = :ville order by c.fin desc")
	List<Praticien> orderByDate();
	
}
