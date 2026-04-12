package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreerReleveMedicaleRequest {
    private String diagnostic;
    private String symptomes;
    private String notes;
    private String typeVisite;
    private Integer dureeConsultation;
    private Long patientId;
    private Long doctorId;
}