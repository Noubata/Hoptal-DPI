package beny.hoptal.services;

import beny.hoptal.data.models.Patient;
import beny.hoptal.dtos.requests.AjouterAllergieRequest;
import beny.hoptal.dtos.requests.AjouterAntecedentRequest;
import beny.hoptal.dtos.requests.CreerPatientRequest;
import beny.hoptal.dtos.responses.AjouterAllergieResponse;
import beny.hoptal.dtos.responses.AjouterAntecedentResponse;
import beny.hoptal.dtos.responses.CreerPatientResponse;
import beny.hoptal.dtos.responses.DossierCompletResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientService {
    @Transactional
    CreerPatientResponse creerPatient(CreerPatientRequest request);

    DossierCompletResponse getDossierComplet(Long patientId);

//    List<CreerPatientResponse> rechercherPatient(String query);

    List<CreerPatientResponse> rechercherPatient();

    List<CreerPatientResponse> getRecentPatients();

    @Transactional
    AjouterAllergieResponse ajouterAllergie(Long patientId,
                                            AjouterAllergieRequest request);

    @Transactional
    AjouterAntecedentResponse ajouterAntecedent(Long patientId,
                                                AjouterAntecedentRequest request);

    // Add these 3 methods to PatientService
    CreerPatientResponse getPatientById(Long patientId);

    List<AjouterAllergieResponse> getAllergies(Long patientId);

    List<AjouterAntecedentResponse> getAntecedents(Long patientId);
}
