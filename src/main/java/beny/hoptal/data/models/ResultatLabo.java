package beny.hoptal.data.models;

import java.time.LocalDateTime;

public class ResultatLabo {
    private Long id;
    private String nomDuTest;
    private String nomDuLabo;
    private String resultat;
    private String valeurNormale;
    private String unite;
    private Boolean anomalie;
    private LocalDateTime dateDeTest;
    private String commentaire;
    private StatusResultat statut;
    private Patient patient;
    private ReleveMedicale releveMedicale;
    private Laborantin laborantin;
}
