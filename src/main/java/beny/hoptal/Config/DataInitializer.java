package beny.hoptal.Config;

import beny.hoptal.data.models.*;
import beny.hoptal.data.repositories.*;
import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.dtos.requests.CreerPatientRequest;
import beny.hoptal.services.DocteurService;
import beny.hoptal.services.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final DocteurService docteurService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HopitalRepository hopitalRepository;
    private final SpecialiteRepository specialiteRepository;
    private final DepartementRepository departementRepository;
    private final PatientService patientService;
    private final ServiceRepository serviceRepository;

    public DataInitializer(
            RoleRepository roleRepository,
            UserRepository userRepository,
            DocteurService docteurService,
            BCryptPasswordEncoder passwordEncoder,
            HopitalRepository hopitalRepository,
            SpecialiteRepository specialiteRepository,
            DepartementRepository departementRepository,
            PatientService patientService,
            ServiceRepository serviceRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.docteurService = docteurService;
        this.passwordEncoder = passwordEncoder;
        this.hopitalRepository = hopitalRepository;
        this.specialiteRepository = specialiteRepository;
        this.departementRepository = departementRepository;
        this.patientService = patientService;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void run(String... args) {
        initRoles();
        Specialite specialite = initSpecialites();
        Hopital hopital = initHopital();
        Departement departement = initDepartements(hopital);
        initServices(departement);
        initAdminUser();
        initDoctorUser(specialite, departement);
        initPatientUser(hopital);
    }
    private void initRoles() {
        List.of("ADMIN", "DOCTOR", "LABORANTIN", "PATIENT")
                .forEach(this::createRoleIfNotExists);
    }

    private void createRoleIfNotExists(String nom) {
        if (roleRepository.findByNom(nom).isEmpty()) {
            Role role = new Role();
            role.setNom(nom);
            roleRepository.save(role);
            System.out.println("==> Role created : " + nom);
        }
    }

    private Specialite initSpecialites() {
        if (specialiteRepository.count() == 0) {
            List<String> noms = List.of(
                    "Médecine Générale", "Pédiatrie", "Chirurgie",
                    "Gynécologie", "Cardiologie", "Neurologie",
                    "Radiologie", "Anesthésiologie", "Dermatologie"
            );
            noms.forEach(nom -> {
                Specialite s = new Specialite();
                s.setNom(nom);
                specialiteRepository.save(s);
            });
            System.out.println("==> " + noms.size() + " spécialités créées");
        }
        return specialiteRepository.findAll().get(0);
    }

    private Hopital initHopital() {
        if (hopitalRepository.count() == 0) {
            Hopital hopital = new Hopital();  // ← new Hopital() pas null
            hopital.setNomDuHopital("Hôpital Général de Référence Nationale");
            hopital.setAdresse("N'Djamena, Tchad");
            hopital.setVille(Ville.ABECHE);
            hopital.setPays("Tchad");
            hopital.setPhoneNumber("+235 00 00 00 00");
            hopital.setEmail("hgrn@hoptal.td");
            hopital.setTypeHopital(TypeHopital.PUBLIC);
            Hopital saved = hopitalRepository.save(hopital);
            System.out.println("==> Hôpital créé avec ID : " + saved.getId());
            return saved;
        }
        return hopitalRepository.findAll().get(0);
    }
    private Departement initDepartements(Hopital hopital) {
        if (departementRepository.count() == 0) {
            List<String> noms = List.of(
                    "Médecine Interne", "Chirurgie", "Pédiatrie",
                    "Maternité", "Urgences", "Laboratoire"
            );
            noms.forEach(nom -> {
                Departement d = new Departement();
                d.setNom(nom);
                d.setTypeDepartement(TypeDepartement.MEDICAL);
                d.setHopital(hopital);
                departementRepository.save(d);
            });
            System.out.println("==> " + noms.size() + " départements créés");
        }
        return departementRepository.findAll().get(0);
    }

    private void initServices(Departement departement) {
        if (serviceRepository.count() == 0) {
            List<String> noms = List.of(
                    "Laboratoire Central", "Radiologie",
                    "Consultation Générale", "Urgences",
                    "Bloc Opératoire", "Maternité"
            );
            noms.forEach(nom -> {
                Service service = new Service();
                service.setNom(nom);
                service.setTypeService(TypeService.URGENCE);
                service.setDepartement(departement);
                service.setDescription("Bienvenue à " + nom);
                service.setEtage("Étage 2");
                serviceRepository.save(service);
            });
            System.out.println("==> " + noms.size() + " services créés");
        }
    }

    private void initAdminUser() {
        if (userRepository.findByNomUtilisateur("admin").isPresent()) return;

        Role adminRole = roleRepository.findByNom("ADMIN")
                .orElseThrow(() -> new RuntimeException("Rôle ADMIN introuvable"));

        User admin = new User();
        admin.setNomUtilisateur("admin");
        admin.setMotDePasseHashe(passwordEncoder.encode("admin1234"));
        admin.setActif(true);
        admin.setEmail("admin@gmail.com");
        admin.setDateCreation(LocalDateTime.now());
        admin.setRole(adminRole);
        userRepository.save(admin);
        System.out.println("==> Admin créé : admin / admin1234");
    }
    private void initDoctorUser(Specialite specialite, Departement departement) {
        if (userRepository.findByNomUtilisateur("dr.ali").isPresent()) return;

        CreerDocteurRequest request = new CreerDocteurRequest();
        request.setNom("Ali");
        request.setPrenom("Hassan");
        request.setNumeroDeLicence("LIC-001");
        request.setNumeroDeTelephone("0612345678");
        request.setEmail("ali.hassan@hoptal.td");
        request.setDateEmbauche(LocalDate.now());
        request.setSpecialiteId(specialite.getId());
        request.setDepartementId(departement.getId());
        request.setNomUtilisateur("dr.ali");
        request.setMotDePasse("doctor1234");
        docteurService.creerDoctor(request);
        System.out.println("==> Médecin créé : dr.ali / doctor1234");
    }

    private void initPatientUser(Hopital hopital) {
        if (userRepository.findByNomUtilisateur("mr.djim").isPresent()) return;

        CreerPatientRequest request = new CreerPatientRequest();
        request.setNomUtilisateur("mr.djim");
        request.setMotDePasse("patient1234");
        request.setNom("Madje-neleum");
        request.setPrenom("Bobadoumadje");
        request.setEmail("djim@gmail.com");
        request.setTelephone("2345678998");
        request.setGenre(Genre.MASCULIN);
        request.setAdresse("18 folarin st");
        request.setDateDeNaissance(LocalDate.of(2002, 1, 1));
        request.setTypeSang(TypeSang.A_POSITIF);
        request.setNomContactUrgence("Bobadoumadje");
        request.setTelephoneContactUrgence("12345678");
        request.setEmailContactUrgence("boba@gmail.com");
        request.setHopitalId(hopital.getId());
        patientService.creerPatient(request);
        System.out.println("==> Patient créé : mr.djim / patient1234");
    }
}