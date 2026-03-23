package beny.hoptal.data.models;

import java.time.LocalDateTime;

public class Allergie {
    private Long id;
    private String substance;
    private String severite;
    private String reaction;
    private Patient patient;
    private LocalDateTime date;
}
