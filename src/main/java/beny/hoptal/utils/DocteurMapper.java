package beny.hoptal.utils;

import beny.hoptal.data.models.Docteur;
import beny.hoptal.data.models.Patient;
import beny.hoptal.dtos.responses.CreerDocteurResponse;
import beny.hoptal.dtos.responses.CreerPatientResponse;

public class DocteurMapper {

    public static CreerDocteurResponse toCreerDocteurResponse(Docteur docteur) {
        CreerDocteurResponse response = new CreerDocteurResponse();
        response.setId(docteur.getId());
        response.setNom(docteur.getNom());
        response.setUserId(docteur.getUser().getId());
        response.setPrenom(docteur.getPrenom());
        response.setNumeroDeLicence(docteur.getNumeroDeLicence());
        response.setNumeroDeTelephone(docteur.getNumeroDeTelephone());
        response.setEmail(docteur.getEmail());
        response.setDateEmbauche(docteur.getDateEmbauche());

        // null-safe mappings
        if (docteur.getSpecialite() != null) {
            response.setSpecialiteNom(docteur.getSpecialite().getNom());
        } else {
            response.setSpecialiteNom(null); // or a default label
        }

        if (docteur.getDepartement() != null) {
            response.setDepartementNom(docteur.getDepartement().getNom());
        } else {
            response.setDepartementNom(null);
        }

        if (docteur.getUser() != null) {
            response.setNomUtilisateur(docteur.getUser().getNomUtilisateur());
        } else {
            response.setNomUtilisateur(null);
        }

        return response;
    }

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
}