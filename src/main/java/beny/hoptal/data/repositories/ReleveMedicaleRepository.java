package beny.hoptal.data.repositories;

import beny.hoptal.data.models.ReleveMedicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ReleveMedicaleRepository extends JpaRepository<ReleveMedicale, Long> {
    List<ReleveMedicale> findByPatientId(@Param("patientId") Long patientId);


    List<ReleveMedicale> findByDoctorId(@Param("doctorId") Long doctorId);
}