package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Docteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocteurRepository extends JpaRepository<Docteur, Long> {
    Optional<Docteur> findByNumeroDuLicence(String numeroDuLicence);
}
