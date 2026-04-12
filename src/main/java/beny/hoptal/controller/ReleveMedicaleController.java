package beny.hoptal.controller;

import beny.hoptal.dtos.requests.AjouterPrescriptionRequest;
import beny.hoptal.dtos.requests.CreerReleveMedicaleRequest;
import beny.hoptal.dtos.requests.DemandeAnalyseRequest;
import beny.hoptal.dtos.responses.*;
import beny.hoptal.services.ReleveMedicaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/releves")
public class ReleveMedicaleController {

    private final ReleveMedicaleService releveMedicaleService;

    public ReleveMedicaleController(ReleveMedicaleService releveMedicaleService) {
        this.releveMedicaleService = releveMedicaleService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<CreerReleveMedicaleResponse>> creerReleve(
            @RequestBody CreerReleveMedicaleRequest request) {
        CreerReleveMedicaleResponse creerReleveMedicaleResponse = releveMedicaleService.creerReleve(request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Releve medical cree avec succes", creerReleveMedicaleResponse));
    }

    @GetMapping("/{releveId}")
    public ResponseEntity<APIResponse<CreerReleveMedicaleResponse>> getReleveById(
            @PathVariable Long releveId) {
        CreerReleveMedicaleResponse creerReleveMedicaleResponse = releveMedicaleService.getReleveById(releveId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", creerReleveMedicaleResponse));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<APIResponse<List<CreerReleveMedicaleResponse>>>getHistoriquePatient(
            @PathVariable Long patientId) {
        List<CreerReleveMedicaleResponse> creerReleveMedicaleResponses= releveMedicaleService.getHistoriquePatient(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", creerReleveMedicaleResponses));
    }

    @PostMapping("/{releveId}/prescriptions")
    public ResponseEntity<APIResponse<AjouterPrescriptionResponse>> ajouterPrescription(
            @PathVariable Long releveId,
            @RequestBody AjouterPrescriptionRequest request) {
        AjouterPrescriptionResponse ajouterPrescriptionResponse = releveMedicaleService.ajouterPrescription(releveId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Succes",  ajouterPrescriptionResponse));
    }

    @PostMapping("/{releveId}/demander-analyse")
    public ResponseEntity<APIResponse<ResultatDuLaboResponse>> demanderAnalyse(
            @PathVariable Long releveId,
            @RequestBody DemandeAnalyseRequest request) {
        ResultatDuLaboResponse resultatDuLaboResponse = releveMedicaleService.demanderAnalyse(releveId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Succes",  resultatDuLaboResponse));
    }
}