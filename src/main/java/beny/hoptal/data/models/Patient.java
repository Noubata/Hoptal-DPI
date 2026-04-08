package beny.hoptal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroDossier;
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private LocalDate dateDeNaissance;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String adresse;
    private String email;
    @Enumerated(EnumType.STRING)
    private TypeSang typeSang;
    @Enumerated(EnumType.STRING)
    private StatusPatient statusPatient;
    private String nomContactUrgence;
    private String telephoneContactUrgence;
    private String emailContactUrgence;
    @ManyToOne
    private Hopital hopital;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "patient")
    private List<Allergie> allergies;
    @OneToMany(mappedBy = "patient")
    private List<Antecedent> antecedents;
}
