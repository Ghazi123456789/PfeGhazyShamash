package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Shamash.Pfe.Entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

        PasswordResetToken findByToken(String token);
}
