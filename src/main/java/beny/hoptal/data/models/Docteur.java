package beny.hoptal.data.models;

import java.time.LocalDateTime;

public class Docteur {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroDuLicence;
    private String email;
    private String numeroDeTelephone;
    private LocalDateTime dateDebut;
    private Specialite specialite;
    private Departement departement;
    private User user;
}
