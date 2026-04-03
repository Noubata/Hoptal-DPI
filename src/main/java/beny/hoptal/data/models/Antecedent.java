package beny.hoptal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private boolean chronique;
    @Enumerated(EnumType.STRING)
    private TypeAntecedent typeAntecedent;
    private String description;
    private LocalDate dateDebut;
    private String notes;
    @ManyToOne
    private Patient patient;
}
