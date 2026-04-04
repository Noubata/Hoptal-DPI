package beny.hoptal.dtos.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreerLaborantinResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private String email;
    private String serviceNom;
    private String nomUtilisateur;
}
