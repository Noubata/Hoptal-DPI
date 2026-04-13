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

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           DocteurService docteurService,
                           BCryptPasswordEncoder passwordEncoder,
                           HopitalRepository hopitalRepository,
                           SpecialiteRepository specialiteRepository,
                           DepartementRepository departementRepository,
                           PatientService patientService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.docteurService = docteurService;
        this.passwordEncoder = passwordEncoder;
        this.hopitalRepository = hopitalRepository;
        this.specialiteRepository = specialiteRepository;
        this.departementRepository = departementRepository;
        this.patientService = patientService;
    }

    @Override
    public void run(String... args) {

        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("DOCTOR");
        createRoleIfNotExists("LABORANTIN");
        createRoleIfNotExists("PATIENT");

        Specialite special = new Specialite();
        if (specialiteRepository.count() == 0) {
            Specialite specialite = new Specialite();
            //specialite.setId(specialite.getId());
            specialite.setNom("Médecine Générale");
            specialite.setDescription("Médecine générale");
            specialite.setCode("MG");
            special = specialiteRepository.save(specialite);
            System.out.println("==> Specialite created : Médecine Générale");
        }

        Hopital hopital = null;
        if (hopitalRepository.count() == 0) {
            hopital.setNomDuHopital("Hôpital Général de Référence Nationale");
            hopital.setAdresse("N'Djamena, Tchad");
            hopital.setVille(Ville.ABECHE);
            hopital.setPays("Tchad");
            hopital.setPhoneNumber("+235 00 00 00 00");
            hopital.setEmail("hgrn@hoptal.td");
            hopital.setTypeHopital(TypeHopital.PUBLIC);
            hopitalRepository.save(hopital);
            System.out.println("==> Hopital created");
        }else{
            hopital =hopitalRepository.findAll().get(0);
        }

        Departement depart = new Departement();
        if (departementRepository.count() == 0) {
            Hopital hoptal = hopitalRepository.findAll().get(0);
            Departement departement = new Departement();
            departement.setNom("Médecine Interne");
            departement.setTypeDepartement(TypeDepartement.MEDICAL);
            departement.setDescription("Département de médecine interne");
            departement.setHopital(hoptal);
            //departement.setId(departement.getId());
            depart = departementRepository.save(departement);
            System.out.println("==> Departement created with id : " + departement.getId());
        }

        if (userRepository.findByNomUtilisateur("admin").isEmpty()) {
            Role adminRole = roleRepository.findByNom("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN role not found"));

            User admin = new User();
            admin.setNomUtilisateur("admin");
            admin.setMotDePasseHashe(passwordEncoder.encode("admin1234"));
            admin.setActif(true);
            admin.setEmail("admin@gmail.com");
            admin.setDateCreation(LocalDateTime.now());
            admin.setRole(adminRole);
            userRepository.save(admin);
            System.out.println("==> Admin created : admin / admin1234");
        }

        if (userRepository.findByNomUtilisateur("dr.ali").isEmpty()) {
            CreerDocteurRequest doctorRequest = new CreerDocteurRequest();
            doctorRequest.setNom("Ali");
            doctorRequest.setPrenom("Hassan");
            doctorRequest.setNumeroDeLicence("LIC-001");
            doctorRequest.setNumeroDeTelephone("0612345678");
            doctorRequest.setEmail("ali.hassan@hoptal.td");
            doctorRequest.setDateEmbauche(LocalDate.now());
            doctorRequest.setSpecialiteId(special.getId());
            doctorRequest.setDepartementId(depart.getId());
            doctorRequest.setNomUtilisateur("dr.ali");
            doctorRequest.setMotDePasse("doctor1234");
            docteurService.creerDoctor(doctorRequest);
            System.out.println("==> Doctor created : dr.ali / doctor1234");
        }
        Patient monPatient = new Patient();
        if(userRepository.findByNomUtilisateur("mr.djim").isEmpty()){
            CreerPatientRequest patient = new CreerPatientRequest();
            patient.setNomUtilisateur("mr.djim");
            patient.setNom("Madje-neleum");
            patient.setPrenom("Bobadoumadje");
            patient.setEmail("djim@gmail.com");
            patient.setTelephone("2345678998");
            patient.setEmailContactUrgence("boba@gmail.com");
            patient.setGenre(Genre.MASCULIN);
            patient.setAdresse("18 folarin st");
            patient.setMotDePasse("patient1234");
            patient.setDateDeNaissance(LocalDate.of(2002,01,01));
            patient.setTypeSang(TypeSang.A_POSITIF);
            patient.setNomContactUrgence("Bobadoumadje");
            patient.setTelephoneContactUrgence("12345678");
            patient.setHopitalId(hopital.getId());
            patientService.creerPatient(patient);
            System.out.println("==> Patient created : mr.djim / patient1234");
        }
    }

    private void createRoleIfNotExists(String nom) {
        if (roleRepository.findByNom(nom).isEmpty()) {
            Role role = new Role();
            role.setNom(nom);
            roleRepository.save(role);
            System.out.println("==> Role created : " + nom);
        }
    }
}