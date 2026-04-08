package beny.hoptal.dtos.requests;

import lombok.Data;

@Data
public class DemandeAnalyseRequest {
    private String nomDuTest;
    private String nomDuLabo;
    private Long laborantinId;
}