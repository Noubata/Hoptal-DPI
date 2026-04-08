package beny.hoptal.utils;

import beny.hoptal.data.models.ResultatLabo;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;

public class ResultatLaboMapper {
    public static ResultatDuLaboResponse toEvolutionResponse(ResultatLabo resultatLabo) {
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
