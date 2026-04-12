package beny.hoptal.services;

import beny.hoptal.data.models.*;
import beny.hoptal.data.repositories.*;
import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.dtos.requests.CreerUserRequest;
import beny.hoptal.dtos.responses.CreerDocteurResponse;
import beny.hoptal.dtos.responses.CreerPatientResponse;
import beny.hoptal.exceptions.*;
import beny.hoptal.utils.DocteurMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocteurServiceImpl implements DocteurService {

        private final DocteurRepository docteurRepository;
        private final SpecialiteRepository specialiteRepository;
        private final DepartementRepository departementRepository;
        private final ReleveMedicaleRepository releveMedicaleRepository;
        private final RoleRepository roleRepository;
        private final UserService userService;
        private final UserRepository userRepository;

        public DocteurServiceImpl(DocteurRepository docteurRepository,
                             SpecialiteRepository specialiteRepository,
                             DepartementRepository departementRepository,
                             ReleveMedicaleRepository releveMedicaleRepository,
                             RoleRepository roleRepository,
                             UserService userService,
                                  UserRepository userRepository) {
            this.docteurRepository = docteurRepository;
            this.specialiteRepository = specialiteRepository;
            this.departementRepository = departementRepository;
            this.releveMedicaleRepository = releveMedicaleRepository;
            this.roleRepository = roleRepository;
            this.userService = userService;
            this.userRepository = userRepository;
        }

        @Transactional
        @Override
        public CreerDocteurResponse creerDoctor(CreerDocteurRequest request) {

            if (docteurRepository.findByNumeroDeLicence(request.getNumeroDeLicence()).isPresent()) {
                throw new NumeroLicenceExisteDeja("Un médecin avec le numéro de licence '"
                        + request.getNumeroDeLicence() + "' existe déjà.");
            }

            Specialite specialite = specialiteRepository.findByNom(request.getNom())
                   .orElseThrow(() -> new SpecialiteIntrouvable("Spécialité introuvable."));

            Departement departement = departementRepository.findByNom(request.getNom())
                    .orElseThrow(() -> new DepartementIntrouvableException("Département introuvable."));

            Role role = roleRepository.findByNom("DOCTOR")
                    .orElseThrow(() -> new RoleDocteurIntrouvableException("Rôle DOCTOR introuvable."));

            CreerUserRequest userRequest = new CreerUserRequest();
            userRequest.setNomUtilisateur(request.getNomUtilisateur());
            userRequest.setMotDePasse(request.getMotDePasse());
            userRequest.setRoleId(role.getId());
            userRequest.setEmail(request.getEmail());
            User user = userService.creerUser(userRequest);
            userRepository.save(user);

            Docteur doctor = new Docteur();
            doctor.setNom(request.getNom());
            doctor.setPrenom(request.getPrenom());
            doctor.setNumeroDeLicence(request.getNumeroDeLicence());
            doctor.setNumeroDeTelephone(request.getNumeroDeTelephone());
            doctor.setEmail(request.getEmail());
            doctor.setDateEmbauche(request.getDateEmbauche());
            doctor.setSpecialite(specialite);
            doctor.setDepartement(departement);
            doctor.setUser(user);

            return DocteurMapper.toCreerDocteurResponse(docteurRepository.save(doctor));
        }

        @Override
        public List<CreerPatientResponse> getPatientsDoctor(Long doctorId) {

            docteurRepository.findById(doctorId)
                    .orElseThrow(() -> new MedecinIntrouvableException("Médecin introuvable."));
            return releveMedicaleRepository.findByDoctorId(doctorId)
                    .stream()
                    .map(releve -> releve.getPatient())
                    .distinct()
                    .map(DocteurMapper::toCreerPatientResponse)
                    .toList();
        }
        @Override
        public List<CreerDocteurResponse> rechercherDoctor(String query) {
            return docteurRepository.findByNom(query)
                    .stream()
                    .map(DocteurMapper::toCreerDocteurResponse)
                    .toList();
        }
        @Override
        public CreerDocteurResponse getDoctorById(Long doctorId) {
            Docteur docteur = docteurRepository.findById(doctorId)
                    .orElseThrow(() -> new MedecinIntrouvableException("Médecin introuvable."));
            return DocteurMapper.toCreerDocteurResponse(docteur);
        }

}
