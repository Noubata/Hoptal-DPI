package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Patient;

import java.util.Optional;

public interface PatientRepository {
    Optional<Patient> findByNumeroDuDossier(String numeroDuDossier);
}
