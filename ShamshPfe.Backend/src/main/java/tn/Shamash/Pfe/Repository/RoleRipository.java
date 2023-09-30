package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Shamash.Pfe.Entity.Role;
import tn.Shamash.Pfe.Enum.ERole;

import java.util.Optional;

public interface RoleRipository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
