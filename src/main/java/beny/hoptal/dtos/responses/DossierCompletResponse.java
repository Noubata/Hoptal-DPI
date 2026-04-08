package beny.hoptal.dtos.responses;

import lombok.Data;
import java.util.List;

@Data
public class DossierCompletResponse {
    private CreerPatientResponse patient;
    private List<AjouterAllergieResponse> allergies;
    private List<AjouterAntecedentResponse> antecedents;
    private List<ReleveMedicaleResponse> derniersReleves;
    private List<AjouterPrescriptionResponse> prescriptionsActives;
    private List<ResultatDuLaboResponse> derniersResultats;
}