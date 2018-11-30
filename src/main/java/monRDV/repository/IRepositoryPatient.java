package monRDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monRDV.model.Patient;

public interface IRepositoryPatient extends JpaRepository<Patient, Long>{

}
