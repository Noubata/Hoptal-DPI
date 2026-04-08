package beny.hoptal.services;

import beny.hoptal.data.models.*;
import beny.hoptal.data.repositories.*;
import beny.hoptal.dtos.requests.AjouterPrescriptionRequest;
import beny.hoptal.dtos.requests.CreerReleveMedicaleRequest;
import beny.hoptal.dtos.requests.DemandeAnalyseRequest;
import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;
import beny.hoptal.dtos.responses.CreerReleveMedicaleResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import beny.hoptal.exceptions.*;
import beny.hoptal.utils.ReleveMedicaleMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReleveMedicaleServiceImpl implements ReleveMedicaleService {

        private final ReleveMedicaleRepository releveMedicaleRepository;
        private final PrescriptionRepository prescriptionRepository;
        private final ResultatLaboRepository resultatLaboRepository;
        private final PatientRepository patientRepository;
        private final DocteurRepository docteurRepository;
        private final LaborantinRepository laborantinRepository;
        private final AllergieRepository allergieRepository;

        public ReleveMedicaleServiceImpl(ReleveMedicaleRepository releveMedicaleRepository,
                                     PrescriptionRepository prescriptionRepository,
                                     ResultatLaboRepository resultatLaboRepository,
                                     PatientRepository patientRepository,
                                     DocteurRepository docteurRepository,
                                     LaborantinRepository laborantinRepository,
                                     AllergieRepository allergieRepository) {
            this.releveMedicaleRepository = releveMedicaleRepository;
            this.prescriptionRepository = prescriptionRepository;
            this.resultatLaboRepository = resultatLaboRepository;
            this.patientRepository = patientRepository;
            this.docteurRepository = docteurRepository;
            this.laborantinRepository = laborantinRepository;
            this.allergieRepository = allergieRepository;
        }
        @Transactional
        @Override
        public CreerReleveMedicaleResponse creerReleve(CreerReleveMedicaleRequest request) {

            Patient patient = patientRepository.findById(request.getPatientId())
                    .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

            Docteur doctor = docteurRepository.findById(request.getDoctorId())
                    .orElseThrow(() -> new MedecinIntrouvableException("Médecin introuvable."));

            ReleveMedicale releve = new ReleveMedicale();
            releve.setDiagnostic(request.getDiagnostic());
            releve.setSymptomes(request.getSymptomes());
            releve.setNotes(request.getNotes());
            releve.setTypeVisite(request.getTypeVisite());
            releve.setDureeConsultation(request.getDureeConsultation());
            releve.setDateDeVisite(LocalDateTime.now());
            releve.setPatient(patient);
            releve.setDoctor(doctor);

            return ReleveMedicaleMapper.toCreerReleveMedicaleResponse(releveMedicaleRepository.save(releve));
        }

        @Transactional
        @Override
        public AjouterPrescriptionResponse ajouterPrescription(Long releveId,
                                                               AjouterPrescriptionRequest request) {

            ReleveMedicale releve = releveMedicaleRepository.findById(releveId)
                    .orElseThrow(() -> new ReleveIntrouvableException("Relevé médical introuvable."));

            Long patientId = releve.getPatient().getId();
            List<Allergie> allergies = allergieRepository.findByPatientId(patientId);

            List<String> conflits = allergies.stream()
                    .filter(a -> request.getNomDuMedicament().toLowerCase()
                            .contains(a.getSubstance().toLowerCase())
                            ||
                            a.getSubstance().toLowerCase()
                                    .contains(request.getNomDuMedicament().toLowerCase()))
                    .map(Allergie::getSubstance)
                    .toList();

            // 3. Block if dangerous allergy detected
            if (!conflits.isEmpty()) {
                throw new AllergieDecterException(
                        "DANGER — Patient allergique à : " + String.join(", ", conflits)
                                + ". Prescription bloquée.");
            }

            // 4. Save prescription
            Prescription prescription = new Prescription();
            prescription.setNomDuMedicament(request.getNomDuMedicament());
            prescription.setDosage(request.getDosage());
            prescription.setFrequence(request.getFrequence());
            prescription.setDuree(request.getDuree());
            prescription.setInstructions(request.getInstructions());
            prescription.setRenouvellable(request.getRenouvellable());
            prescription.setDateDePrescription(LocalDateTime.now());
            prescription.setReleveMedicale(releve);

            return ReleveMedicaleMapper.toAjouterPrescriptionResponse(prescriptionRepository.save(prescription));
        }

        @Transactional
        @Override
        public ResultatDuLaboResponse demanderAnalyse(Long releveId,
                                                      DemandeAnalyseRequest request) {

            ReleveMedicale releve = releveMedicaleRepository.findById(releveId)
                    .orElseThrow(() -> new ReleveIntrouvableException("Relevé médical introuvable."));

            Laborantin laborantin = laborantinRepository.findById(request.getLaborantinId())
                    .orElseThrow(() -> new LaborantinIntrouvableException("Laborantin introuvable."));

            ResultatLabo resultat = new ResultatLabo();
            resultat.setNomDuTest(request.getNomDuTest());
            resultat.setNomDuLabo(request.getNomDuLabo());
            resultat.setStatutResultat(StatusResultat.EN_ATTENTE);
            resultat.setAnomalie(false);
            resultat.setPatient(releve.getPatient());
            resultat.setReleveMedicale(releve);
            resultat.setLaborantin(laborantin);

            return ReleveMedicaleMapper.toResultatDuLaboResponse(resultatLaboRepository.save(resultat));
        }
        @Override
        public List<CreerReleveMedicaleResponse> getHistoriquePatient(Long patientId) {

            patientRepository.findById(patientId)
                    .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

            return releveMedicaleRepository.findByPatientId(patientId)
                    .stream()
                    .map(ReleveMedicaleMapper::toCreerReleveMedicaleResponse)
                    .toList();
        }
        @Override
        public CreerReleveMedicaleResponse getReleveById(Long releveId) {
            ReleveMedicale releve = releveMedicaleRepository.findById(releveId)
                    .orElseThrow(() -> new ReleveIntrouvableException("Relevé médical introuvable."));
            return ReleveMedicaleMapper.toCreerReleveMedicaleResponse(releve);
        }
    }
