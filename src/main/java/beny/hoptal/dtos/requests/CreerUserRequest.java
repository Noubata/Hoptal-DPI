package beny.hoptal.dtos.requests;

import beny.hoptal.data.models.TypeDepartement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreerUserRequest {
    private String nomUtilisateur;
    private String motDePasse;
    private String email;
    private TypeDepartement departement;
    private Long roleId;
}