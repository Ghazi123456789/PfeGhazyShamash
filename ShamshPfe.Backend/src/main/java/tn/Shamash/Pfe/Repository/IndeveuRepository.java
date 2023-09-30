package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Shamash.Pfe.Entity.Indevedu;
@Repository
public interface IndeveuRepository extends JpaRepository<Indevedu,Long> {

}
