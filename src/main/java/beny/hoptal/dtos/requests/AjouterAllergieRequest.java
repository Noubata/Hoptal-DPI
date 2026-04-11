package beny.hoptal.dtos.requests;

import lombok.Data;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AjouterAllergieRequest {
    private String substance;
    private String severite;
    private String reaction;
    private LocalDate dateDecouverte;
}