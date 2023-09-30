package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Shamash.Pfe.Entity.FileAttachment;

@Repository
public interface FileRepository extends JpaRepository<FileAttachment, Long>{

}
