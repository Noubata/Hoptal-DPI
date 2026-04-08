package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface HopitalRepository extends JpaRepository<Hopital, Long> {
    Optional<Hopital> findByNomDuHopital(@Param("nomDuHopital") String nomDuHopital);
}