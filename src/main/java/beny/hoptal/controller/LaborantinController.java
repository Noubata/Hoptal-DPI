package beny.hoptal.controller;

import beny.hoptal.dtos.requests.CreerLaborantinRequest;
import beny.hoptal.dtos.requests.SaisirResultatRequest;
import beny.hoptal.dtos.responses.APIResponse;
import beny.hoptal.dtos.responses.CreerLaborantinResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import beny.hoptal.dtos.responses.ServiceResponse;
import beny.hoptal.services.LaborantinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laborantins")
public class LaborantinController {

    private final LaborantinService laborantinService;

    public LaborantinController(LaborantinService laborantinService) {
        this.laborantinService = laborantinService;
    }

    @PostMapping("/creer-laborantin")
    public ResponseEntity<APIResponse<CreerLaborantinResponse>> creerLaborantin(
            @RequestBody CreerLaborantinRequest request) {
        CreerLaborantinResponse response = laborantinService.creerLaborantin(request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Compte laborantin cree avec succes", response));
    }

    @GetMapping("/api/services")
    public ResponseEntity<APIResponse<List<ServiceResponse>>> getServices() {
        List<ServiceResponse> services = laborantinService.getAllServices();
        return ResponseEntity.ok(new APIResponse<>("Services récupérés", services));
    }

    @GetMapping("/getLaborantins")
    public ResponseEntity<APIResponse<List<CreerLaborantinResponse>>> getAllLaborantins() {
        List<CreerLaborantinResponse> laborantins = laborantinService.getAllLaborantins();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse<>("succes", laborantins));
    }

    @GetMapping("/{laborantinId}/demandes-en-attente")
    public ResponseEntity<APIResponse<List<ResultatDuLaboResponse>>> getDemandesEnAttente(
            @PathVariable Long laborantinId) {
        List<ResultatDuLaboResponse> result = laborantinService.getDemandesEnAttente(laborantinId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("succes", result));
    }

    @PutMapping("/resultats/{resultatId}/saisir")
    public ResponseEntity<APIResponse<ResultatDuLaboResponse>> saisirResultat(
            @PathVariable Long resultatId,
            @RequestBody SaisirResultatRequest request) {
        ResultatDuLaboResponse resultatDuLaboResponse = laborantinService.saisirResultat(resultatId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("succes", resultatDuLaboResponse));
    }
}