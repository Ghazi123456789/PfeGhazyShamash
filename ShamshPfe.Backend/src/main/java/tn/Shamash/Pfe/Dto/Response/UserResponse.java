package tn.Shamash.Pfe.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Instant dateDeNaissance;
    private String departement;
    private String phoneNumber;
    private String poste;
    private int age;
}
