package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
    Specialite findById(long specialiteId);

    Optional<Specialite> findByNom(String nom);
}
