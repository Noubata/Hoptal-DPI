package beny.hoptal.dtos.requests;

import lombok.Data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DemandeAnalyseRequest {
    private String nomDuTest;
    private String nomDuLabo;
    private Long laborantinId;
}