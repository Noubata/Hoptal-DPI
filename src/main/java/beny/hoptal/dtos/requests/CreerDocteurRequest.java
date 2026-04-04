package beny.hoptal.dtos.requests;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreerDocteurRequest {
    private String nom;
    private String prenom;
    private String numeroDeLicence;
    private String numeroDeTelephone;
    private String email;
    private LocalDate dateEmbauche;
    private Long specialiteId;
    private Long departementId;
    // User account
    private String nomUtilisateur;
    private String motDePasse;
}