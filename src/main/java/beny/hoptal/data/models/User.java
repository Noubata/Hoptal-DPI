package beny.hoptal.data.models;

import java.time.LocalDateTime;

public class User {
    private Long id;
    private String nomUtilisateur;
    private String motDePasseHashe;
    private Boolean actif;
    private LocalDateTime dateCreation;
    private Role role;
}
