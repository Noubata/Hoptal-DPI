package beny.hoptal.data.repositories;

import beny.hoptal.data.models.ReleveMedicale;

import java.util.Optional;

public interface ReleveMedicaleRepository {
    Optional<ReleveMedicale>  findByPatientId(Long patientId);
}
