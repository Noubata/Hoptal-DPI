package beny.hoptal.utils;

import beny.hoptal.data.models.*;
import beny.hoptal.dtos.responses.*;

public class PatientMapper {
    public static CreerPatientResponse toCreerPatientResponse(Patient patient) {
        CreerPatientResponse response = new CreerPatientResponse();
        response.setId(patient.getId());
        response.setNumeroDossier(patient.getNumeroDossier());
        response.setNom(patient.getNom());
        response.setPrenom(patient.getPrenom());
        response.setDateDeNaissance(patient.getDateDeNaissance());
        response.setGenre(patient.getGenre());
        response.setAdresse(patient.getAdresse());
        response.setTelephone(patient.getNumeroDeTelephone());
        response.setEmail(patient.getEmail());
        response.setTypeSang(patient.getTypeSang());
        response.setStatus(patient.getStatusPatient());
        response.setNomContactUrgence(patient.getNomContactUrgence());
        response.setTelephoneContactUrgence(patient.getTelephoneContactUrgence());
        response.setHopitalNom(patient.getHopital().getNomDuHopital());
        response.setNomUtilisateur(patient.getUser().getNomUtilisateur());
        return response;
    }
    public static AjouterAllergieResponse toAllergieResponse(Allergie allergie) {
        AjouterAllergieResponse response = new AjouterAllergieResponse();
        response.setId(allergie.getId());
        response.setSubstance(allergie.getSubstance());
        response.setSeverite(allergie.getSeverite());
        response.setReaction(allergie.getReaction());
        response.setDateDecouverte(allergie.getDateDecouverte());
        return response;
    }
    public static AjouterAntecedentResponse toAntecedentResponse(Antecedent antecedent) {
        AjouterAntecedentResponse response = new AjouterAntecedentResponse();
        response.setId(antecedent.getId());
        response.setType(antecedent.getTypeAntecedent());
        response.setDescription(antecedent.getDescription());
        response.setDateDebut(antecedent.getDateDebut());
        response.setChronique(antecedent.isChronique());
        response.setNotes(antecedent.getNotes());
        return response;
    }
    public static ReleveMedicaleResponse toReleveResponse(ReleveMedicale releveMedicale) {
        ReleveMedicaleResponse response = new ReleveMedicaleResponse();
        response.setId(releveMedicale.getId());
        response.setDiagnostic(releveMedicale.getDiagnostic());
        response.setSymptomes(releveMedicale.getSymptomes());
        response.setNotes(releveMedicale.getNotes());
        response.setDateDeVisite(releveMedicale.getDateDeVisite());
        response.setTypeVisite(releveMedicale.getTypeVisite());
        response.setDureeConsultation(releveMedicale.getDureeConsultation());
        response.setDoctorNom(releveMedicale.getDoctor().getNom());
        response.setDoctorPrenom(releveMedicale.getDoctor().getPrenom());
        return response;
    }

    public static AjouterPrescriptionResponse toPrescriptionResponse(Prescription prescription) {
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

    public static CreerPatientResponse toResponseRecentPatients(Patient patient) {
        CreerPatientResponse response = new CreerPatientResponse();

        response.setNom(patient.getNom());
        response.setPrenom(patient.getPrenom());
        response.setEmail(patient.getEmail());
        response.setNumeroDossier(patient.getNumeroDossier());

        return response;
    }
    public static ResultatDuLaboResponse toResultatResponse(ResultatLabo resultatLabo) {
        ResultatDuLaboResponse response = new ResultatDuLaboResponse();
        response.setId(resultatLabo.getId());
        response.setNomDuTest(resultatLabo.getNomDuTest());
        response.setNomDuLabo(resultatLabo.getNomDuLabo());
        response.setResultat(resultatLabo.getResultat());
        response.setValeurNormale(resultatLabo.getValeurNormale());
        response.setUnite(resultatLabo.getUnite());
        response.setAnomalie(resultatLabo.getAnomalie());
        response.setDateDeTest(resultatLabo.getDateDeTest());
        response.setCommentaire(resultatLabo.getCommentaire());
        response.setStatut(resultatLabo.getStatutResultat() != null ? resultatLabo.getStatutResultat().name() : null);
        response.setPatientNom(resultatLabo.getPatient().getNom());
        response.setPatientPrenom(resultatLabo.getPatient().getPrenom());
        response.setPatientNumeroDossier(resultatLabo.getPatient().getNumeroDossier());
        if (resultatLabo.getLaborantin() != null) {
            response.setLaborantinNom(resultatLabo.getLaborantin().getNom());
            response.setLaborantinPrenom(resultatLabo.getLaborantin().getPrenom());
        }
        return response;
    }
}
