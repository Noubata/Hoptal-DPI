package beny.hoptal.controller;

import beny.hoptal.data.models.Departement;
import beny.hoptal.data.repositories.DepartementRepository;
import beny.hoptal.dtos.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {
    private final DepartementRepository departementRepository;
    public DepartementController(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<Departement>>> getAllDepartements() {
        return ResponseEntity.ok(new APIResponse<>("ok", departementRepository.findAll()));
    }
}
