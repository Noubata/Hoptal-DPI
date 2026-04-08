package beny.hoptal.dtos.responses;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResultatDuLaboResponse {
    private Long id;
    private String nomDuTest;
    private String nomDuLabo;
    private String resultat;
    private String valeurNormale;
    private String unite;
    private Boolean anomalie;
    private LocalDateTime dateDeTest;
    private String commentaire;
    private String statut;
    private String patientNom;
    private String patientPrenom;
    private String patientNumeroDossier;
    private String laborantinNom;
    private String laborantinPrenom;
}