package beny.hoptal.dtos.requests;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AjouterAllergieRequest {
    private String substance;
    private String severite;
    private String reaction;
    private LocalDate dateDecouverte;
}