package beny.hoptal.services;

import beny.hoptal.dtos.responses.AjouterAllergieResponse;
import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;

import java.util.List;

public interface PrescriptionService {
    List<AjouterPrescriptionResponse> getPrescriptionsActives(Long patientId);

    List<AjouterAllergieResponse> verifierAllergiesMedicament(Long patientId,
                                                              String nomMedicament);
}
