package beny.hoptal.data.repositories;

import beny.hoptal.data.models.ResultatLabo;
import beny.hoptal.data.models.StatusResultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ResultatLaboRepository extends JpaRepository<ResultatLabo, Long> {

    List<ResultatLabo> findByPatientId(@Param("patientId") Long patientId);

    List<ResultatLabo> findByReleveMedicaleId(@Param("releveId") Long releveId);

    List<ResultatLabo> findByLaborantinId(@Param("laborantinId") Long laborantinId);

    List<ResultatLabo> findByStatutResultat(@Param("statutResultat") StatusResultat statutResulat);

    List<ResultatLabo> findByPatientId(@Param("patientId") Long patientId, @Param("nomDuTest") String nomDuTest);
}