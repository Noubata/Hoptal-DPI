package beny.hoptal.services;

import beny.hoptal.dtos.requests.AjouterPrescriptionRequest;
import beny.hoptal.dtos.requests.CreerReleveMedicaleRequest;
import beny.hoptal.dtos.requests.DemandeAnalyseRequest;
import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;
import beny.hoptal.dtos.responses.CreerReleveMedicaleResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ReleveMedicaleService{
    @Transactional
    CreerReleveMedicaleResponse creerReleve(CreerReleveMedicaleRequest request);

    @Transactional
    AjouterPrescriptionResponse ajouterPrescription(Long releveId,
                                                    AjouterPrescriptionRequest request);

    @Transactional
    ResultatDuLaboResponse demanderAnalyse(Long releveId,
                                           DemandeAnalyseRequest request);

    List<CreerReleveMedicaleResponse> getHistoriquePatient(Long patientId);

    CreerReleveMedicaleResponse getReleveById(Long releveId);
}
