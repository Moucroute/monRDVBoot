package monRDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monRDV.model.RendezVous;

public interface IRepositoryRendezVous extends JpaRepository<RendezVous, Long> {
	
}
