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
public class Antecedent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeAntecedent typeAntecedent;
    private String description;
    private boolean chronique;
    private LocalDateTime dateDebut;
    private String notes;
    @ManyToOne
    private Patient patient;
}
