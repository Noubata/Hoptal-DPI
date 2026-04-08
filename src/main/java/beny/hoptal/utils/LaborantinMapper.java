package beny.hoptal.utils;

import beny.hoptal.data.models.Laborantin;
import beny.hoptal.data.models.ResultatLabo;
import beny.hoptal.dtos.responses.CreerLaborantinResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;

public class LaborantinMapper {
    public static CreerLaborantinResponse toCreerLaborantinResponse(Laborantin laborantin) {
        CreerLaborantinResponse response = new CreerLaborantinResponse();
        response.setId(laborantin.getId());
        response.setNom(laborantin.getNom());
        response.setPrenom(laborantin.getPrenom());
        response.setNumeroDeTelephone(laborantin.getNumeroDeTelephone());
        response.setEmail(laborantin.getEmail());
        response.setServiceNom(laborantin.getService().getNom());
        response.setNomUtilisateur(laborantin.getUser().getNomUtilisateur());
        return response;
    }
    public static ResultatDuLaboResponse toSaisirResultatResponse(ResultatLabo resultatLabo) {
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
        response.setStatut(resultatLabo.getStatutResultat().name());
        response.setPatientNom(resultatLabo.getPatient().getNom());
        response.setPatientPrenom(resultatLabo.getPatient().getPrenom());
        response.setPatientNumeroDossier(resultatLabo.getPatient().getNumeroDossier());
        response.setLaborantinNom(resultatLabo.getLaborantin().getNom());
        response.setLaborantinPrenom(resultatLabo.getLaborantin().getPrenom());
        return response;
    }
}
