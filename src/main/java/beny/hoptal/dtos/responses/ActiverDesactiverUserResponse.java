package beny.hoptal.dtos.responses;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActiverDesactiverUserResponse {
    private Long id;
    private String nomUtilisateur;
    private Boolean actif;
    private LocalDateTime dateCreation;
    private String role;
}