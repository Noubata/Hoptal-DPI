package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
    Optional<Prescription> findByReleveMedicaleId(Long releveMedicaleId);
    Optional<Prescription> findByReleveMedicalePatientId(Long releveMedicalePatientId);
    @Query("SELECT p FROM Prescription p WHERE p.releveMedicale.patient.id = :patientId")
    List<Prescription> findByPatientId(@Param("patientId") Long patientId);
}
