package beny.hoptal.dtos.requests;

import beny.hoptal.data.models.Genre;
import beny.hoptal.data.models.TypeSang;
import lombok.Data;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreerPatientRequest {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private Genre genre;
    private String adresse;
    private String telephone;
    private String email;
    private TypeSang typeSang;
    private String nomContactUrgence;
    private String telephoneContactUrgence;
    private Long hopitalId;
    // User account
    private String nomUtilisateur;
    private String motDePasse;
}