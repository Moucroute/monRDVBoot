package monRDV.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import monRDV.model.Utilisateur;

public interface IRepositoryUtilisateur extends JpaRepository<Utilisateur, Long>{


}
