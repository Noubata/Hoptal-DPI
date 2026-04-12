package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartementRepository extends JpaRepository<Departement, Long> {
    List<Departement> findByHopitalId(@Param("hopitalId") Long hopitalId);

    Optional<Departement> findByNom(String nom);
}
