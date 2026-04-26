package beny.hoptal.services;

import beny.hoptal.dtos.requests.CreerLaborantinRequest;
import beny.hoptal.dtos.requests.SaisirResultatRequest;
import beny.hoptal.dtos.responses.CreerLaborantinResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LaborantinService {
    @Transactional
    CreerLaborantinResponse creerLaborantin(CreerLaborantinRequest request);

    List<ResultatDuLaboResponse> getDemandesEnAttente(Long laborantinId);

    List<CreerLaborantinResponse> getAllLaborantins();

    @Transactional
    ResultatDuLaboResponse saisirResultat(Long laborantinId, SaisirResultatRequest request);
}
