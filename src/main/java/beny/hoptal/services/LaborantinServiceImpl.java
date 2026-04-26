package beny.hoptal.services;

import beny.hoptal.data.models.*;
import beny.hoptal.data.repositories.LaborantinRepository;
import beny.hoptal.data.repositories.ResultatLaboRepository;
import beny.hoptal.data.repositories.RoleRepository;
import beny.hoptal.data.repositories.ServiceRepository;
import beny.hoptal.dtos.requests.CreerLaborantinRequest;
import beny.hoptal.dtos.requests.CreerUserRequest;
import beny.hoptal.dtos.requests.SaisirResultatRequest;
import beny.hoptal.dtos.responses.CreerLaborantinResponse;
import beny.hoptal.dtos.responses.ResultatDuLaboResponse;
import beny.hoptal.exceptions.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import beny.hoptal.utils.LaborantinMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LaborantinServiceImpl implements LaborantinService {

        private final LaborantinRepository laborantinRepository;
        private final ResultatLaboRepository resultatLaboRepository;
        private final ServiceRepository serviceRepository;
        private final RoleRepository roleRepository;
        private final UserService userService;

        public LaborantinServiceImpl(LaborantinRepository laborantinRepository,
                                 ResultatLaboRepository resultatLaboRepository,
                                 ServiceRepository serviceRepository,
                                 RoleRepository roleRepository,
                                 UserService userService) {
            this.laborantinRepository = laborantinRepository;
            this.resultatLaboRepository = resultatLaboRepository;
            this.serviceRepository = serviceRepository;
            this.roleRepository = roleRepository;
            this.userService = userService;
        }

        @Transactional
        @Override
        public CreerLaborantinResponse creerLaborantin(CreerLaborantinRequest request) {

            beny.hoptal.data.models.Service service = (beny.hoptal.data.models.Service) serviceRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new ServiceIntrouvableException("Service introuvable."));

            Role role = roleRepository.findByNom("LABORANTIN")
                    .orElseThrow(() -> new RoleLaborantinIntrouvableException("Rôle LABORANTIN introuvable."));

            CreerUserRequest userRequest = new CreerUserRequest();
            userRequest.setNomUtilisateur(request.getNomUtilisateur());
            userRequest.setMotDePasse(request.getMotDePasse());
            userRequest.setRoleId(role.getId());
            User user = userService.creerUser(userRequest);

            Laborantin laborantin = new Laborantin();
            laborantin.setNom(request.getNom());
            laborantin.setPrenom(request.getPrenom());
            laborantin.setNumeroDeTelephone(request.getNumeroDeTelephone());
            laborantin.setEmail(request.getEmail());
            laborantin.setService(service);
            laborantin.setUser(user);
            Laborantin toSave = laborantinRepository.save(laborantin);

            return LaborantinMapper.toCreerLaborantinResponse(toSave);
        }
        @Override
        public List<ResultatDuLaboResponse> getDemandesEnAttente(Long laborantinId) {

            laborantinRepository.findById(laborantinId)
                    .orElseThrow(() -> new LaborantinIntrouvableException("Laborantin introuvable."));

            return resultatLaboRepository
                    .findByLaborantinId(laborantinId)
                    .stream()
                    .filter(r -> r.getStatutResultat() == StatusResultat.EN_ATTENTE)
                    .map(LaborantinMapper::toSaisirResultatResponse)
                    .toList();
        }
        @Override
        public List<CreerLaborantinResponse> getAllLaborantins() {
            return laborantinRepository.findAll()
                .stream()
                .map(LaborantinMapper::toCreerLaborantinResponse)
                .toList();
        }

        @Transactional
        @Override
        public ResultatDuLaboResponse saisirResultat(Long laborantinId, SaisirResultatRequest request) {
            List<ResultatLabo> resultats = resultatLaboRepository.findByLaborantinId(laborantinId);

            ResultatLabo resultatLabo = resultats.stream()
                    .findFirst()
                    .orElseThrow(() -> new DemandeAnalyseIntrouvable("Demande d'analyse introuvable."));
            if (resultatLabo.getResultat() != null) {
                throw new ResultatDejaSaisi("Ce résultat a déjà été saisi.");
            }
            resultatLabo.setResultat(request.getResultat());
            resultatLabo.setValeurNormale(request.getValeurNormale());
            resultatLabo.setUnite(request.getUnite());
            resultatLabo.setCommentaire(request.getCommentaire());
            resultatLabo.setDateDeTest(LocalDateTime.now());
            resultatLabo.setAnomalie(
                    detecterAnomalie(
                            request.getResultat(),
                            request.getValeurNormale()
                    )
            );

            resultatLabo.setStatutResultat(StatusResultat.DISPONIBLE);
            ResultatLabo saved = resultatLaboRepository.save(resultatLabo);

            return LaborantinMapper.toSaisirResultatResponse(saved);
        }
        private boolean detecterAnomalie(String resultat, String valeurNormale) {
            try {
                // valeurNormale format : "70-100" or "<200" or ">40"
                double valeur = Double.parseDouble(resultat.trim());

                if (valeurNormale.contains("-")) {
                    String[] parts = valeurNormale.split("-");
                    double min = Double.parseDouble(parts[0].trim());
                    double max = Double.parseDouble(parts[1].trim());
                    return valeur < min || valeur > max;
                }
                if (valeurNormale.startsWith("<")) {
                    double max = Double.parseDouble(valeurNormale.substring(1).trim());
                    return valeur >= max;
                }
                if (valeurNormale.startsWith(">")) {
                    double min = Double.parseDouble(valeurNormale.substring(1).trim());
                    return valeur <= min;
                }
                return false;
            } catch (NumberFormatException e) {
                // result is not numeric — can't detect anomalie automatically
                return false;
            }
        }
}
