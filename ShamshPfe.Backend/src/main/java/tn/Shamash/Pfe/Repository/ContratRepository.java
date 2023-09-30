package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Shamash.Pfe.Entity.Contrat;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long>{

}
