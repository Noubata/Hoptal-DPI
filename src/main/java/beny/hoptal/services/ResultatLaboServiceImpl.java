package beny.hoptal.services;

import beny.hoptal.data.repositories.PatientRepository;
import beny.hoptal.data.repositories.ResultatLaboRepository;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import beny.hoptal.exceptions.PatientIntrouvable;
import beny.hoptal.utils.ResultatLaboMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatLaboServiceImpl implements ResultatLaboService {

    private final ResultatLaboRepository resultatLaboRepository;
    private final PatientRepository patientRepository;

    public ResultatLaboServiceImpl(ResultatLaboRepository resultatLaboRepository,
                                   PatientRepository patientRepository) {
        this.resultatLaboRepository = resultatLaboRepository;
        this.patientRepository = patientRepository;
    }
    @Override
    public List<ResultatDuLaboResponse> getResultatsAnormaux(Long patientId) {

        patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

        return resultatLaboRepository.findByPatientId(patientId)
                .stream()
                .filter(r -> r.getAnomalie() != null && r.getAnomalie())
                .map(ResultatLaboMapper::toEvolutionResponse)
                .toList();
    }
    @Override
    public List<ResultatDuLaboResponse> getEvolutionResultat(Long patientId,
                                                             String nomDuTest) {

        patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientIntrouvable("Patient introuvable."));

        return resultatLaboRepository.findByPatientId(patientId, nomDuTest)
                .stream()
                .map(ResultatLaboMapper::toEvolutionResponse)
                .toList();
    }
}
