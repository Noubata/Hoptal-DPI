package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Patient;
import beny.hoptal.dtos.responses.CreerPatientResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByNumeroDossier(@Param("numeroDossier") String numeroDossier);
    List<Patient> findByHopitalId(@Param("hopitalId") Long hopitalId);
    List<Patient> findByNom(@Param("nom") String nom);
    List<Patient> findTop5ByOrderByIdDesc();
}