package monRDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monRDV.model.Lieu;

public interface IRepositoryLieu extends JpaRepository<Lieu,Long> {

}
