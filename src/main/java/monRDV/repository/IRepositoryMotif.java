package monRDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monRDV.model.Motif;

public interface IRepositoryMotif extends JpaRepository<Motif,Long> {

}
