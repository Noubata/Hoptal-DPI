package beny.hoptal.data.models;

import java.time.LocalDateTime;
import java.util.List;

public class ReleveMedicale {
    private Long id;
    private String diagnostic;
    private String symptomes;
    private String notes;
    private LocalDateTime dateDeVisite;
    private String typeVisite;
    private Integer dureeConsultation;
    private Patient patient;
    private Docteur doctor;
    private List<Prescriptions> prescriptions;
    private List<ResultatLabo> resultatDuLabos;
}
