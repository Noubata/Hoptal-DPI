package beny.hoptal.dtos.responses;

import beny.hoptal.data.models.Genre;
import beny.hoptal.data.models.StatusPatient;
import beny.hoptal.data.models.TypeSang;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreerPatientResponse {
    private Long id;
    private String numeroDossier;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private Genre genre;
    private String adresse;
    private String telephone;
    private String email;
    private TypeSang typeSang;
    private StatusPatient status;
    private String nomContactUrgence;
    private String telephoneContactUrgence;
    private String hopitalNom;
    private String nomUtilisateur;
}