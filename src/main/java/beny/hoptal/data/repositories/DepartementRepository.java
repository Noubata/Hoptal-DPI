package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    List<Departement> findByHopitalId(@Param("hopitalId") Long hopitalId);
}
