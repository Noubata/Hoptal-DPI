package beny.hoptal.dtos.responses;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AjouterPrescriptionResponse {
    private Long id;
    private String nomDuMedicament;
    private String dosage;
    private String frequence;
    private String duree;
    private String instructions;
    private LocalDateTime dateDePrescription;
    private Boolean renouvellable;
    private Long releveMedicaleId;
    private String patientNom;
    private String patientPrenom;
    private String doctorNom;
    private String doctorPrenom;
}