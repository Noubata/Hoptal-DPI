package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Laborantin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface LaborantinRepository extends JpaRepository<Laborantin, Long> {

    List<Laborantin> findByServiceId(@Param("serviceId") Long serviceId);

    Optional<Laborantin> findByUserId(@Param("userId") Long userId);
}