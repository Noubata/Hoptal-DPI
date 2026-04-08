package beny.hoptal.services;

import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;
import beny.hoptal.dtos.responses.AjouterAllergieResponse;
import beny.hoptal.data.repositories.AllergieRepository;
import beny.hoptal.data.repositories.PatientRepository;
import beny.hoptal.data.repositories.PrescriptionRepository;
import beny.hoptal.exceptions.PatientIntrouvable;
import beny.hoptal.utils.PrescriptionMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AllergieRepository allergieRepository;
    private final PatientRepository patientRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                   AllergieRepository allergieRepository,
                                   PatientRepository patientRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.allergieRepository = allergieRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    public List<AjouterPrescriptionResponse> getPrescriptionsActives(Long patientId) {

        patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

        return prescriptionRepository.findByPatientId(patientId)
                .stream()
                .filter(prescription -> {
                    if (prescription.getDuree() == null || prescription.getDateDePrescription() == null) {
                        return false;
                    }
                    long jours = parseDureeEnJours(prescription.getDuree().toLowerCase());
                    LocalDateTime expiration = prescription.getDateDePrescription().plusDays(jours);
                    return LocalDateTime.now().isBefore(expiration);
                })
                .map(PrescriptionMapper::toPrescriptionActivesResponse)
                .toList();
    }
    @Override
    public List<AjouterAllergieResponse> verifierAllergiesMedicament(Long patientId,
                                                                     String nomMedicament) {

        patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

        return allergieRepository.findByPatientId(patientId)
                .stream()
                .filter(allergie -> nomMedicament.toLowerCase()
                        .contains(allergie.getSubstance().toLowerCase())
                        ||
                        allergie.getSubstance().toLowerCase()
                                .contains(nomMedicament.toLowerCase()))
                .map(PrescriptionMapper::toVerifierAllergieResponse)
                .toList();
    }

    private long parseDureeEnJours(String duree) {
        try {
            String[] parts = duree.trim().split(" ");
            long nombre = Long.parseLong(parts[0]);
            if (duree.contains("mois")) return nombre * 30;
            if (duree.contains("semaine")) return nombre * 7;
            return nombre;
        } catch (Exception e) {
            return 7;
        }
    }
}