package beny.hoptal.dtos.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String nomUtilisateur;
    private String motDePasse;
}
