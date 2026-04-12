package beny.hoptal.controller;

import beny.hoptal.dtos.responses.APIResponse;
import beny.hoptal.dtos.responses.AjouterAllergieResponse;
import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;
import beny.hoptal.services.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/actives/{patientId}")
    public ResponseEntity<APIResponse<List<AjouterPrescriptionResponse>>> getPrescriptionsActives(
            @PathVariable Long patientId) {
        List<AjouterPrescriptionResponse> ajouterPrescriptionResponses = prescriptionService.getPrescriptionsActives(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", ajouterPrescriptionResponses));
    }

    @GetMapping("/verifier")
    public ResponseEntity<APIResponse<List<AjouterAllergieResponse>>> verifierAllergies(
            @RequestParam Long patientId,
            @RequestParam String medicament) {
        List<AjouterAllergieResponse> ajouterAllergieResponses = prescriptionService.verifierAllergiesMedicament(patientId, medicament);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", ajouterAllergieResponses));

    }
}