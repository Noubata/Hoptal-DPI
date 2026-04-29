package beny.hoptal.controller;

import beny.hoptal.data.models.Service;
import beny.hoptal.data.repositories.ServiceRepository;
import beny.hoptal.dtos.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Service>>> getAllServices() {
        return ResponseEntity.ok(new APIResponse<>("ok", serviceRepository.findAll()));
    }
}
