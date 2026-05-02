package beny.hoptal.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDoctorRequest {
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private String email;
    private Long specialiteId;
    private Long departementId;
}