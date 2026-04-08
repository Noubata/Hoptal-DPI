package beny.hoptal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Docteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String numeroDeLicence;
    private String email;
    private String numeroDeTelephone;
    private LocalDate dateEmbauche;
    @ManyToOne
    private Specialite specialite;
    @ManyToOne
    private Departement departement;
    @OneToOne
    private User user;
}
