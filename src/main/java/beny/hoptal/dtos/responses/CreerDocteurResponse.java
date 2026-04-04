package beny.hoptal.dtos.responses;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreerDocteurResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroDeLicence;
    private String numeroDeTelephone;
    private String email;
    private LocalDate dateEmbauche;
    private String specialiteNom;
    private String departementNom;
    private String nomUtilisateur;
}