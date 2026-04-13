package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaisirResultatRequest {
    private String resultat;
    private String valeurNormale;
    private String unite;
    private String commentaire;
}