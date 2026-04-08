package beny.hoptal.services;

import beny.hoptal.data.models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
}
