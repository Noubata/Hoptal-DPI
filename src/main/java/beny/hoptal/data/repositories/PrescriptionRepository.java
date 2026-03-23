package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Prescription;

import java.util.Optional;

public interface PrescriptionRepository {
    Optional<Prescription> findByReleveMedicaleId(Long releveMedicaleId);
    Optional<Prescription> findByReleveMedicalePatientId(Long releveMedicalePatientId);
}
