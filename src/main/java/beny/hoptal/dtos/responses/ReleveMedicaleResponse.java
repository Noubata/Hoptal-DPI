package beny.hoptal.dtos.responses;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReleveMedicaleResponse {
    private Long id;
    private String diagnostic;
    private String symptomes;
    private String notes;
    private LocalDateTime dateDeVisite;
    private String typeVisite;
    private Integer dureeConsultation;
    private String doctorNom;
    private String doctorPrenom;
}