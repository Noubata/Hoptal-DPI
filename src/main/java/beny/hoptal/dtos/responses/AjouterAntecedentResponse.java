package beny.hoptal.dtos.responses;

import beny.hoptal.data.models.TypeAntecedent;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AjouterAntecedentResponse {
    private Long id;
    private TypeAntecedent type;
    private String description;
    private LocalDate dateDebut;
    private Boolean chronique;
    private String notes;
}