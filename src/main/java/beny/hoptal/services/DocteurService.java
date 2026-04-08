package beny.hoptal.services;

import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.dtos.responses.CreerDocteurResponse;
import beny.hoptal.dtos.responses.CreerPatientResponse;

import java.util.List;

public interface DocteurService {
    CreerDocteurResponse creerDoctor(CreerDocteurRequest request);

    List<CreerPatientResponse> getPatientsDoctor(Long doctorId);

    List<CreerDocteurResponse> rechercherDoctor(String query);

    CreerDocteurResponse getDoctorById(Long doctorId);
}
