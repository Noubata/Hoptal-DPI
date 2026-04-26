package beny.hoptal.controller;

import beny.hoptal.dtos.requests.CreerPatientRequest;
import beny.hoptal.dtos.requests.AjouterAllergieRequest;
import beny.hoptal.dtos.requests.AjouterAntecedentRequest;
import beny.hoptal.dtos.responses.*;
import beny.hoptal.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/creerPatient")
    public ResponseEntity<APIResponse<CreerPatientResponse>> creerPatient(
            @RequestBody CreerPatientRequest request) {
        CreerPatientResponse creerPatientResponse = patientService.creerPatient(request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Compte patient avec succes", creerPatientResponse));
    }
    @GetMapping("/recent")
    public ResponseEntity<APIResponse<List<CreerPatientResponse>>> getRecentPatients() {
        List<CreerPatientResponse> creerPatientResponse = patientService.getRecentPatients();
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Derniers Patients", creerPatientResponse));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<APIResponse<CreerPatientResponse>> getPatient(
            @PathVariable Long patientId) {
        CreerPatientResponse creerPatientResponse = patientService.getPatientById(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("succes", creerPatientResponse));
    }

    @GetMapping("/{patientId}/dossier-complet")
    public ResponseEntity<APIResponse<DossierCompletResponse>> getDossierComplet(
            @PathVariable Long patientId) {
        DossierCompletResponse dossierCompletResponse = patientService.getDossierComplet(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", dossierCompletResponse));
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<CreerPatientResponse>>> rechercherPatient(
            @RequestParam String query) {
        List<CreerPatientResponse> resultat = patientService.rechercherPatient();
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("Succes", resultat));
    }

    @PostMapping("/{patientId}/allergies")
    public ResponseEntity<APIResponse<AjouterAllergieResponse>> ajouterAllergie(
            @PathVariable Long patientId,
            @RequestBody AjouterAllergieRequest request) {
        AjouterAllergieResponse ajouterAllergieResponse = patientService.ajouterAllergie(patientId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", ajouterAllergieResponse));
    }

    @GetMapping("/{patientId}/allergies")
    public ResponseEntity<APIResponse<List<AjouterAllergieResponse>>> getAllergies(
            @PathVariable Long patientId) {
        List<AjouterAllergieResponse> response = patientService.getAllergies(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", response));
    }

    @PostMapping("/{patientId}/antecedents")
    public ResponseEntity<APIResponse<AjouterAntecedentResponse>> ajouterAntecedent(
            @PathVariable Long patientId,
            @RequestBody AjouterAntecedentRequest request) {
        AjouterAntecedentResponse ajouterAntecedent = patientService.ajouterAntecedent(patientId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", ajouterAntecedent));
    }

    @GetMapping("/{patientId}/antecedents")
    public ResponseEntity<APIResponse<List<AjouterAntecedentResponse>>> getAntecedents(
            @PathVariable Long patientId) {
        List<AjouterAntecedentResponse> response = patientService.getAntecedents(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>("ok", response));
    }
}