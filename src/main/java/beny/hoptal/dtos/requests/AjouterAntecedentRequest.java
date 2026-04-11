package beny.hoptal.dtos.requests;

import beny.hoptal.data.models.TypeAntecedent;
import lombok.Data;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AjouterAntecedentRequest {
    private TypeAntecedent type;
    private String description;
    private LocalDate dateDebut;
    private Boolean chronique;
    private String notes;
}