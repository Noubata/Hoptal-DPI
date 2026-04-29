package beny.hoptal.dtos.responses;

import beny.hoptal.data.models.TypeService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private Long id;
    private String nom;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeService typeService;
    private String etage;
    private Long departementId;
}
