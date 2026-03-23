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
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomDuMedicament;
    private String dosage;
    private String frequence;
    private String duree;
    private String instructions;
    private LocalDateTime dateDePrescription;
    private Boolean renouvellable;
    @ManyToOne
    private ReleveMedicale releveMedicale;
}
