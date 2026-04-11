package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AjouterPrescriptionRequest {
    private String nomDuMedicament;
    private String dosage;
    private String frequence;
    private String duree;
    private String instructions;
    private Boolean renouvellable;
}