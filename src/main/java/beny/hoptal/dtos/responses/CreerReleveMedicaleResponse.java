package beny.hoptal.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreerReleveMedicaleResponse {

        private Long id;
        private String diagnostic;
        private String symptomes;
        private String notes;
        private LocalDateTime dateDeVisite;
        private String typeVisite;
        private Integer dureeConsultation;
        private String patientNom;
        private String patientPrenom;
        private String patientNumeroDossier;
        private String doctorNom;
        private String doctorPrenom;
        private List<AjouterPrescriptionResponse> prescriptions;
        private List<ResultatDuLaboResponse> resultats;
}
