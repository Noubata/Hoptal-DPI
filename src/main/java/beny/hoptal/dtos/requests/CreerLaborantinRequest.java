package beny.hoptal.dtos.requests;

import lombok.Data;

@Data
public class CreerLaborantinRequest {
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private String email;
    private Long serviceId;
    // User account
    private String nomUtilisateur;
    private String motDePasse;
}