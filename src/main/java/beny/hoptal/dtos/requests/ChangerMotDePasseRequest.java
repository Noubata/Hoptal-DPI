package beny.hoptal.dtos.requests;

import lombok.Data;

@Data
public class ChangerMotDePasseRequest {
    private String email;
    private String ancienMotDePasse;
    private String nouveauMotDePasse;
}