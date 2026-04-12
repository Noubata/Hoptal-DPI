package beny.hoptal.controller;

import beny.hoptal.dtos.responses.APIResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import beny.hoptal.services.ResultatLaboService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultats-labo")
public class ResultatLaboController {

    private final ResultatLaboService resultatDuLaboService;

    public ResultatLaboController(ResultatLaboService resultatDuLaboService) {
        this.resultatDuLaboService = resultatDuLaboService;
    }

    @GetMapping("/anormaux/{patientId}")
    public ResponseEntity<APIResponse<List<ResultatDuLaboResponse>>> getResultatsAnormaux(
            @PathVariable Long patientId) {
        List<ResultatDuLaboResponse> resultat = resultatDuLaboService.getResultatsAnormaux(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Succes", resultat));
    }

    @GetMapping("/evolution")
    public ResponseEntity<APIResponse<List<ResultatDuLaboResponse>>> getEvolutionResultat(
            @RequestParam Long patientId,
            @RequestParam String nomTest) {
        List<ResultatDuLaboResponse> leResult = resultatDuLaboService.getEvolutionResultat(patientId, nomTest);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Ok", leResult));
    }
}