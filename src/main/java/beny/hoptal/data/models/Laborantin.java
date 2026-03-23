package beny.hoptal.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Laborantin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Genre genre;
    private String nom;
    private String prenom;
    private String numeroDeTelephone;
    private String email;
    @ManyToOne
    private Service service;
    @OneToOne
    private User user;
}
