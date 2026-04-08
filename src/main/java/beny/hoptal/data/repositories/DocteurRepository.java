package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Docteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface DocteurRepository extends JpaRepository<Docteur, Long> {
    Optional<Docteur> findByNumeroDeLicence(@Param("licence") String licence);
    List<Docteur> findByDepartementId(@Param("departementId") Long departementId);
    List<Docteur> findBySpecialiteId(@Param("specialiteId") Long specialiteId);
    List<Docteur> findByNom(@Param("nom") String nom);
}