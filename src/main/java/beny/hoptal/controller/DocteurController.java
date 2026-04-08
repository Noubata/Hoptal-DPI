package beny.hoptal.controller;

import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.dtos.responses.APIResponse;
import beny.hoptal.dtos.responses.CreerDocteurResponse;
import beny.hoptal.dtos.responses.CreerPatientResponse;
import beny.hoptal.services.DocteurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DocteurController {

    private final DocteurService docteurService;

    public DocteurController(DocteurService doctorService) {
        this.docteurService = doctorService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<CreerDocteurResponse>> creerDoctor(
            @RequestBody CreerDocteurRequest request) {
        CreerDocteurResponse creerDocteurResponse = docteurService.creerDoctor(request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, creerDocteurResponse));
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<APIResponse<CreerDocteurResponse>> getDoctorById(
            @PathVariable Long doctorId) {
        CreerDocteurResponse creerDocteurResponse = docteurService.getDoctorById(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, creerDocteurResponse));
    }

    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<APIResponse<List<CreerPatientResponse>>> getPatientsDoctor(
            @PathVariable Long doctorId) {
        List<CreerPatientResponse> creerPatientResponse = docteurService.getPatientsDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, creerPatientResponse));
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<List<CreerDocteurResponse>>> rechercherDoctor(
            @RequestParam String query) {
        List<CreerDocteurResponse> creerDocteur = docteurService.rechercherDoctor(query);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, creerDocteur));
    }
}