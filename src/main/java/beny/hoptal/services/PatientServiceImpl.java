package beny.hoptal.services;

import beny.hoptal.dtos.requests.*;
import beny.hoptal.dtos.responses.*;
import beny.hoptal.data.models.*;
import beny.hoptal.data.repositories.*;
import beny.hoptal.exceptions.PatientIntrouvable;
import beny.hoptal.utils.PatientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

import static beny.hoptal.utils.PatientMapper.toCreerPatientResponse;

@Service
public class PatientServiceImpl implements PatientService {
        private final PatientRepository patientRepository;
        private final AllergieRepository allergieRepository;
        private final AntecedentRepository antecedentRepository;
        private final ReleveMedicaleRepository releveMedicaleRepository;
        private final PrescriptionRepository prescriptionRepository;
        private final ResultatLaboRepository resultatLaboRepository;
        private final HopitalRepository hopitalRepository;
        private final RoleRepository roleRepository;
        private final UserService userService;

        public PatientServiceImpl(PatientRepository patientRepository,
                              AllergieRepository allergieRepository,
                              AntecedentRepository antecedentRepository,
                              ReleveMedicaleRepository releveMedicaleRepository,
                              PrescriptionRepository prescriptionRepository,
                              ResultatLaboRepository resultatLaboRepository,
                              HopitalRepository hopitalRepository,
                              RoleRepository roleRepository,
                              UserService userService) {
            this.patientRepository = patientRepository;
            this.allergieRepository = allergieRepository;
            this.antecedentRepository = antecedentRepository;
            this.releveMedicaleRepository = releveMedicaleRepository;
            this.prescriptionRepository = prescriptionRepository;
            this.resultatLaboRepository = resultatLaboRepository;
            this.hopitalRepository = hopitalRepository;
            this.roleRepository = roleRepository;
            this.userService = userService;
        }

        @Transactional
        @Override
        public CreerPatientResponse creerPatient(CreerPatientRequest request) {

            Hopital hopital = hopitalRepository.findById(request.getHopitalId())
                    .orElseThrow(() -> new RuntimeException("Hôpital introuvable."));

            Role role = roleRepository.findByNom("PATIENT")
                    .orElseThrow(() -> new RuntimeException("Rôle PATIENT introuvable."));

            String numeroDossier = genererNumeroDossier();

            CreerUserRequest userRequest = new CreerUserRequest();
            userRequest.setNomUtilisateur(request.getNomUtilisateur());
            userRequest.setMotDePasse(request.getMotDePasse());
            userRequest.setRoleId(role.getId());
            User user = userService.creerUser(userRequest);

            Patient patient = new Patient();
            patient.setNumeroDossier(numeroDossier);
            patient.setNom(request.getNom());
            patient.setPrenom(request.getPrenom());
            patient.setDateDeNaissance(request.getDateDeNaissance());
            patient.setGenre(request.getGenre());
            patient.setAdresse(request.getAdresse());
            patient.setNumeroDeTelephone(request.getTelephone());
            patient.setEmail(request.getEmail());
            patient.setTypeSang(request.getTypeSang());
            patient.setStatusPatient(StatusPatient.ACTIF);
            patient.setNomContactUrgence(request.getNomContactUrgence());
            patient.setTelephoneContactUrgence(request.getTelephoneContactUrgence());
            patient.setHopital(hopital);
            patient.setUser(user);

            return toCreerPatientResponse(patientRepository.save(patient));
        }
        @Override
        public DossierCompletResponse getDossierComplet(Long patientId) {

            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new RuntimeException("Patient introuvable."));

            List<AjouterAllergieResponse> allergies = allergieRepository
                    .findByPatientId(patientId)
                    .stream()
                    .map(PatientMapper::toAllergieResponse)
                    .toList();

            List<AjouterAntecedentResponse> antecedents = antecedentRepository
                    .findByPatientId(patientId)
                    .stream()
                    .map(PatientMapper::toAntecedentResponse)
                    .toList();

            List<ReleveMedicaleResponse> derniersReleves = releveMedicaleRepository
                    .findByPatientId(patientId)
                    .stream()
                    .limit(5)
                    .map(PatientMapper::toReleveResponse)
                    .toList();

            List<AjouterPrescriptionResponse> prescriptionsActives = prescriptionRepository
                    .findByReleveMedicalePatientId(patientId)
                    .stream()
                    .filter(p -> {
                        if (p.getDuree() == null || p.getDateDePrescription() == null) return false;
                        long jours = parseDureeEnJours(p.getDuree().toLowerCase());
                        return LocalDateTime.now().isBefore(
                                p.getDateDePrescription().plusDays(jours));
                    })
                    .map(PatientMapper::toPrescriptionResponse)
                    .toList();

            List<ResultatDuLaboResponse> derniersResultats = resultatLaboRepository
                    .findByPatientId(patientId)
                    .stream()
                    .limit(5)
                    .map(PatientMapper::toResultatResponse)
                    .toList();

            DossierCompletResponse dossier = new DossierCompletResponse();
            dossier.setPatient(toCreerPatientResponse(patient));
            dossier.setAllergies(allergies);
            dossier.setAntecedents(antecedents);
            dossier.setDerniersReleves(derniersReleves);
            dossier.setPrescriptionsActives(prescriptionsActives);
            dossier.setDerniersResultats(derniersResultats);

            return dossier;
        }
        @Override
        public List<CreerPatientResponse> rechercherPatient(String query) {
            List<Patient> toSearch = patientRepository.findByNom(query);
            return toSearch
                    .stream()
                    .map(PatientMapper::toCreerPatientResponse)
                    .toList();
        }

        @Transactional
        @Override
        public AjouterAllergieResponse ajouterAllergie(Long patientId,
                                                       AjouterAllergieRequest request) {

            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

            Allergie allergie = new Allergie();
            allergie.setSubstance(request.getSubstance());
            allergie.setSeverite(request.getSeverite());
            allergie.setReaction(request.getReaction());
            allergie.setDateDecouverte(request.getDateDecouverte());
            allergie.setPatient(patient);

            return PatientMapper.toAllergieResponse(allergieRepository.save(allergie));
        }

        @Transactional
        @Override
        public AjouterAntecedentResponse ajouterAntecedent(Long patientId,
                                                           AjouterAntecedentRequest request) {

            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

            Antecedent antecedent = new Antecedent();
            antecedent.setTypeAntecedent(request.getType());
            antecedent.setDescription(request.getDescription());
            antecedent.setDateDebut(request.getDateDebut());
            antecedent.setChronique(request.getChronique());
            antecedent.setNotes(request.getNotes());
            antecedent.setPatient(patient);

            return PatientMapper.toAntecedentResponse(antecedentRepository.save(antecedent));
        }

    // Add these 3 methods to PatientService
    @Override
    public CreerPatientResponse getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient introuvable."));
        return PatientMapper.toCreerPatientResponse(patient);
    }
    @Override
    public List<AjouterAllergieResponse> getAllergies(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient introuvable."));
        return allergieRepository.findByPatientId(patientId)
                .stream()
                .map(PatientMapper::toAllergieResponse)
                .toList();
    }
    @Override
    public List<AjouterAntecedentResponse> getAntecedents(Long patientId) {
        patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient introuvable."));
        return antecedentRepository.findByPatientId(patientId)
                .stream()
                .map(PatientMapper::toAntecedentResponse)
                .toList();
    }

        private String genererNumeroDossier() {
            int annee = Year.now().getValue();
            long count = patientRepository.count() + 1;
            return String.format("DPI-%d-%05d", annee, count);
        }

        private long parseDureeEnJours(String duree) {
            try {
                String[] parts = duree.trim().split(" ");
                long nombre = Long.parseLong(parts[0]);
                if (duree.contains("mois"))    return nombre * 30;
                if (duree.contains("semaine")) return nombre * 7;
                return nombre;
            } catch (Exception e) {
                return 7;
            }
        }
}
