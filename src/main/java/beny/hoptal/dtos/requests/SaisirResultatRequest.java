package beny.hoptal.dtos.requests;

import lombok.Data;

@Data
public class SaisirResultatRequest {
    private String resultat;
    private String valeurNormale;
    private String unite;
    private String commentaire;
}