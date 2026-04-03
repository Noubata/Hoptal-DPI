package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Allergie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface AllergieRepository extends JpaRepository<Allergie, Long> {
    List<Allergie> findByPatientId(@Param("patientId") Long patientId);
}