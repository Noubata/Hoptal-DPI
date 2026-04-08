package beny.hoptal.data.repositories;

import beny.hoptal.data.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {


    List<beny.hoptal.data.models.Service> findByDepartementId(@Param("departementId") Long departementId);
}