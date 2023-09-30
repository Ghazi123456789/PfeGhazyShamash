package tn.Shamash.Pfe.serviceImpl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import tn.Shamash.Pfe.Entity.confirmationMailTokenEntity;
import tn.Shamash.Pfe.Repository.ConfirmationMailRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    UserDetailsServiceImpl userService;
    private final ConfirmationMailRepository confirmationTokenRepository;

    public void saveConfirmationToken(confirmationMailTokenEntity token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<confirmationMailTokenEntity> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateCreatedAtByToken(
                LocalDateTime.now(),token);
    }

}