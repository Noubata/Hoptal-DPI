package beny.hoptal.data.repositories;

import beny.hoptal.data.models.ResultatLabo;

import java.util.Optional;

public interface ResultatLaboRepository {
    Optional<ResultatLabo> findByReleveMedicaleId(Long releveMedicaleId);
    Optional<ResultatLabo> findByPatientId(Long  patientId);
    Optional<ResultatLabo>   findByLaborantinId(Long  laborantinId);
}
