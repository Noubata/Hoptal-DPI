package beny.hoptal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultatLabo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomDuTest;
    private String nomDuLabo;
    private String resultat;
    private String valeurNormale;
    private String unite;
    private Boolean anomalie;
    private LocalDateTime dateDeTest;
    private String commentaire;
    @Enumerated(EnumType.STRING)
    private StatusResultat statut;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private ReleveMedicale releveMedicale;
    @ManyToOne
    private Laborantin laborantin;
}
