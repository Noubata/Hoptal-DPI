package beny.hoptal.utils;

import beny.hoptal.data.models.Allergie;
import beny.hoptal.data.models.Prescription;
import beny.hoptal.dtos.responses.AjouterAllergieResponse;
import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;

public class PrescriptionMapper {
    public static AjouterPrescriptionResponse toPrescriptionActivesResponse(Prescription prescription) {
        AjouterPrescriptionResponse response = new AjouterPrescriptionResponse();
        response.setId(prescription.getId());
        response.setNomDuMedicament(prescription.getNomDuMedicament());
        response.setDosage(prescription.getDosage());
        response.setFrequence(prescription.getFrequence());
        response.setDuree(prescription.getDuree());
        response.setInstructions(prescription.getInstructions());
        response.setDateDePrescription(prescription.getDateDePrescription());
        response.setRenouvellable(prescription.getRenouvellable());
        response.setReleveMedicaleId(prescription.getReleveMedicale().getId());
        response.setPatientNom(prescription.getReleveMedicale().getPatient().getNom());
        response.setPatientPrenom(prescription.getReleveMedicale().getPatient().getPrenom());
        response.setDoctorNom(prescription.getReleveMedicale().getDoctor().getNom());
        response.setDoctorPrenom(prescription.getReleveMedicale().getDoctor().getPrenom());
        return response;
    }
    public static AjouterAllergieResponse toVerifierAllergieResponse(Allergie allergie) {
        AjouterAllergieResponse response = new AjouterAllergieResponse();
        response.setId(allergie.getId());
        response.setSubstance(allergie.getSubstance());
        response.setSeverite(allergie.getSeverite());
        response.setReaction(allergie.getReaction());
        response.setDateDecouverte(allergie.getDateDecouverte());
        return response;
    }
}
