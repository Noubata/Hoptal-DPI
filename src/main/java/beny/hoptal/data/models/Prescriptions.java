package beny.hoptal.data.models;

import java.time.LocalDateTime;

public class Prescriptions {
    private Long id;
    private String nomDuMedicament;
    private String dosage;
    private String frequence;
    private String duree;
    private String instructions;
    private LocalDateTime dateDePrescription;
    private Boolean renouvellable;
    private ReleveMedicale releveMedicale;
}
