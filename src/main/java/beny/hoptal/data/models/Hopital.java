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
public class Hopital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomDuHopital;
    @Enumerated(EnumType.STRING)
    private TypeHopital typeHopital;
    private String pays;
    private String adresse;
    private Ville ville;
    private String phoneNumber;
    private String email;
}
