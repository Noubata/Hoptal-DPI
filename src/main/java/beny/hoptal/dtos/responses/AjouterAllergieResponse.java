package beny.hoptal.dtos.responses;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AjouterAllergieResponse {
    private Long id;
    private String substance;
    private String severite;
    private String reaction;
    private LocalDate dateDecouverte;
}