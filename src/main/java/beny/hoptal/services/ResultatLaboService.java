package beny.hoptal.services;

import beny.hoptal.dtos.responses.ResultatDuLaboResponse;

import java.util.List;

public interface ResultatLaboService {
    List<ResultatDuLaboResponse> getResultatsAnormaux(Long patientId);

    List<ResultatDuLaboResponse> getEvolutionResultat(Long patientId,
                                                      String nomDuTest);
}
