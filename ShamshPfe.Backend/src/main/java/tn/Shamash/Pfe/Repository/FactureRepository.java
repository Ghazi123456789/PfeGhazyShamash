package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Shamash.Pfe.Entity.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long>{

}
