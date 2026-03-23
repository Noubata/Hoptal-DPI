package beny.hoptal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReleveMedicale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnostic;
    private String symptomes;
    private String notes;
    private LocalDateTime dateDeVisite;
    private String typeVisite;
    private Integer dureeConsultation;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Docteur doctor;
    @OneToMany(mappedBy = "releveMedicale")
    private List<Prescription> prescriptions;
    @OneToMany(mappedBy = "releveMedicale")
    private List<ResultatLabo> resultatDuLabos;
}
