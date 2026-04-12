package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    private String nomUtilisateur;
    private String motDePasse;
}
