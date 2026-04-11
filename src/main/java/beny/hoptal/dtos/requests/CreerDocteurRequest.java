package beny.hoptal.dtos.requests;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private String nomUtilisateur;
    private String motDePasse;
}