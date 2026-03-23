package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Laborantin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaborantinRepository extends JpaRepository<Laborantin, Long> {
    Optional<Laborantin> findByServiceId(Long service);

}
