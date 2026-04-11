package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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