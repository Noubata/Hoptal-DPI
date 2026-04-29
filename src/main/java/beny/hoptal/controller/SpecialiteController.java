package beny.hoptal.controller;

import beny.hoptal.data.models.Specialite;
import beny.hoptal.data.repositories.SpecialiteRepository;
import beny.hoptal.dtos.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specialites")
public class SpecialiteController {
    private final SpecialiteRepository specialiteRepository;

    public SpecialiteController(SpecialiteRepository specialiteRepository) {
        this.specialiteRepository = specialiteRepository;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Specialite>>> getAllSpecialites() {
        return ResponseEntity.ok(new APIResponse<>("ok", specialiteRepository.findAll()));
    }
}
