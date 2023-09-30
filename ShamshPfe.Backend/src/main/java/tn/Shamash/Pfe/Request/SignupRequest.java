package tn.Shamash.Pfe.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String Username;

    private String Name;
    private String phone;

    private String email;
    private String CIN;

    private Set<String> roles;


    private String password;
    private String Description;
    

}
