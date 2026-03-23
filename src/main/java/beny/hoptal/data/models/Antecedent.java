package beny.hoptal.data.models;

import java.time.LocalDateTime;

public class Antecedent {
    private Long id;
    private TypeAntecedent typeAntecedent;
    private String description;
    private boolean chronique;
    private LocalDateTime dateDebut;
    private String notes;
    private Patient patient;
}
