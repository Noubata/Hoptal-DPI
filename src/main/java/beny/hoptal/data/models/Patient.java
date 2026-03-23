package beny.hoptal.data.models;

import java.util.List;

public class Patient {
    private Long id;
    private String numeroDuDossier;
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private String dateDeNaissance;
    private Genre genre;
    private String addresse;
    private String email;
    private TypeSang typeSang;
    private StatusPatient statusPatient;
    private String nomContactUrgence;
    private String telephoneContactUrgence;
    private String emailContactUrgence;
    private Hopital hopital;
    private User user;
    private List<Allergie> allergies;
    private List<Antecedent> antecedents;
}
