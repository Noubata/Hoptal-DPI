package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangerMotDePasseRequest {
    private String email;
    private String ancienMotDePasse;
    private String nouveauMotDePasse;
}