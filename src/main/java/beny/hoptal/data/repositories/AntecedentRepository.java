package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Antecedent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AntecedentRepository extends JpaRepository<Antecedent, Long> {
    List<Antecedent> findByPatientId(@Param("patientId") Long patientId);
}