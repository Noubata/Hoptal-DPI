package beny.hoptal.dtos.requests;

import beny.hoptal.data.models.TypeDepartement;
import lombok.Data;

@Data
public class CreerUserRequest {
    private String nomUtilisateur;
    private String motDePasse;
    private TypeDepartement departement;
    private Long roleId;
}