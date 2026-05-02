package beny.hoptal.utils;

import beny.hoptal.data.models.Prescription;
import beny.hoptal.data.models.ReleveMedicale;
import beny.hoptal.data.models.ResultatLabo;
import beny.hoptal.data.repositories.PrescriptionRepository;
import beny.hoptal.data.repositories.ResultatLaboRepository;
import beny.hoptal.dtos.responses.AjouterPrescriptionResponse;
import beny.hoptal.dtos.responses.CreerReleveMedicaleResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component  // ← ajouter
public class ReleveMedicaleMapper {

    private final PrescriptionRepository prescriptionRepository;    // ← instance, pas static
    private final ResultatLaboRepository resultatLaboRepository;    // ← instance, pas static

    public ReleveMedicaleMapper(PrescriptionRepository prescriptionRepository,
                                ResultatLaboRepository resultatLaboRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.resultatLaboRepository = resultatLaboRepository;
    }

    // ← supprimer "static" sur toutes les méthodes
    public AjouterPrescriptionResponse toAjouterPrescriptionResponse(Prescription p) {
        AjouterPrescriptionResponse response = new AjouterPrescriptionResponse();
        response.setId(p.getId());
        response.setNomDuMedicament(p.getNomDuMedicament());
        response.setDosage(p.getDosage());
        response.setFrequence(p.getFrequence());
        response.setDuree(p.getDuree());
        response.setInstructions(p.getInstructions());
        response.setDateDePrescription(p.getDateDePrescription());
        response.setRenouvellable(p.getRenouvellable());
        response.setReleveMedicaleId(p.getReleveMedicale().getId());
        response.setPatientNom(p.getReleveMedicale().getPatient().getNom());
        response.setPatientPrenom(p.getReleveMedicale().getPatient().getPrenom());
        response.setDoctorNom(p.getReleveMedicale().getDoctor().getNom());
        response.setDoctorPrenom(p.getReleveMedicale().getDoctor().getPrenom());
        return response;
    }

    public ResultatDuLaboResponse toResultatDuLaboResponse(ResultatLabo resultatLabo) {
        ResultatDuLaboResponse response = new ResultatDuLaboResponse();
        response.setId(resultatLabo.getId());
        response.setNomDuTest(resultatLabo.getNomDuTest());
        response.setNomDuLabo(resultatLabo.getNomDuLabo());
        response.setResultat(resultatLabo.getResultat());
        response.setValeurNormale(resultatLabo.getValeurNormale());
        response.setUnite(resultatLabo.getUnite());
        response.setAnomalie(resultatLabo.getAnomalie());
        response.setDateDeTest(resultatLabo.getDateDeTest());
        response.setCommentaire(resultatLabo.getCommentaire());
        response.setStatut(resultatLabo.getStatutResultat() != null ? resultatLabo.getStatutResultat().name() : null);
        response.setPatientNom(resultatLabo.getPatient().getNom());
        response.setPatientPrenom(resultatLabo.getPatient().getPrenom());
        response.setPatientNumeroDossier(resultatLabo.getPatient().getNumeroDossier());
        if (resultatLabo.getLaborantin() != null) {
            response.setLaborantinNom(resultatLabo.getLaborantin().getNom());
            response.setLaborantinPrenom(resultatLabo.getLaborantin().getPrenom());
        }
        return response;
    }

    public CreerReleveMedicaleResponse toCreerReleveMedicaleResponse(ReleveMedicale r) {
        CreerReleveMedicaleResponse response = new CreerReleveMedicaleResponse();
        response.setId(r.getId());
        response.setDiagnostic(r.getDiagnostic());
        response.setSymptomes(r.getSymptomes());
        response.setNotes(r.getNotes());
        response.setDateDeVisite(r.getDateDeVisite());
        response.setTypeVisite(r.getTypeVisite());
        response.setDureeConsultation(r.getDureeConsultation());
        response.setPatientNom(r.getPatient().getNom());
        response.setPatientPrenom(r.getPatient().getPrenom());
        response.setPatientNumeroDossier(r.getPatient().getNumeroDossier());
        response.setDoctorNom(r.getDoctor().getNom());
        response.setDoctorPrenom(r.getDoctor().getPrenom());

        List<AjouterPrescriptionResponse> prescriptions = prescriptionRepository
                .findByReleveMedicaleId(r.getId())
                .stream()
                .map(this::toAjouterPrescriptionResponse)  // ← this:: pas ReleveMedicaleMapper::
                .toList();
        response.setPrescriptions(prescriptions);

        List<ResultatDuLaboResponse> resultats = resultatLaboRepository
                .findByReleveMedicaleId(r.getId())
                .stream()
                .map(this::toResultatDuLaboResponse)  // ← this:: pas ReleveMedicaleMapper::
                .toList();
        response.setResultats(resultats);

        return response;
    }
}