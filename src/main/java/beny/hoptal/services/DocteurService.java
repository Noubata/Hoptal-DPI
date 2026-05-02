package beny.hoptal.services;

import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.dtos.requests.UpdateDoctorRequest;
import beny.hoptal.dtos.responses.CreerDocteurResponse;
import beny.hoptal.dtos.responses.CreerPatientResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DocteurService {
    CreerDocteurResponse creerDoctor(CreerDocteurRequest request);
    @Transactional
    CreerDocteurResponse updateDoctor(Long doctorId, UpdateDoctorRequest request);

    List<CreerPatientResponse> getPatientsDoctor(Long doctorId);

    List<CreerDocteurResponse> rechercherDoctor();

    CreerDocteurResponse getDoctorById(Long doctorId);
}
