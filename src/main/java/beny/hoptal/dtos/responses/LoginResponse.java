package beny.hoptal.dtos.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String role;
    private Long userId;
    private Long hopitalId;
    private String nomUtilisateur;
}