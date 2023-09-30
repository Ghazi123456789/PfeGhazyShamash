package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.Shamash.Pfe.Entity.confirmationMailTokenEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface ConfirmationMailRepository
        extends JpaRepository<confirmationMailTokenEntity, Long> {

    Optional<confirmationMailTokenEntity> findByToken(String token);

    @org.springframework.transaction.annotation.Transactional
    @Modifying
    @Query("update confirmationMailTokenEntity c set c.createdAt = ?1 where c.token = ?2")
    int updateCreatedAtByToken(LocalDateTime createdAt, String token);



}