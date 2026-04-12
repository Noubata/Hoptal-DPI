package beny.hoptal.Config;

import beny.hoptal.data.models.*;
import beny.hoptal.data.repositories.*;
import beny.hoptal.dtos.requests.CreerDocteurRequest;
import beny.hoptal.services.DocteurService;
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

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           DocteurService docteurService,
                           BCryptPasswordEncoder passwordEncoder,
                           HopitalRepository hopitalRepository,
                           SpecialiteRepository specialiteRepository,
                           DepartementRepository departementRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.docteurService = docteurService;
        this.passwordEncoder = passwordEncoder;
        this.hopitalRepository = hopitalRepository;
        this.specialiteRepository = specialiteRepository;
        this.departementRepository = departementRepository;
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

        Hopital hoptal;
        if (hopitalRepository.count() == 0) {
            Hopital hopital = new Hopital();
            hopital.setNomDuHopital("Hôpital Général de Référence Nationale");
            hopital.setAdresse("N'Djamena, Tchad");
            hopital.setVille(Ville.ABECHE);
            hopital.setPays("Tchad");
            hopital.setPhoneNumber("+235 00 00 00 00");
            hopital.setEmail("hgrn@hoptal.td");
            hopital.setTypeHopital(TypeHopital.PUBLIC);
            hoptal = hopitalRepository.save(hopital);
            System.out.println("==> Hopital created");
        }

        Departement depart = new Departement();
        if (departementRepository.count() == 0) {
            Hopital hopital = hopitalRepository.findAll().get(0);
            Departement departement = new Departement();
            departement.setNom("Médecine Interne");
            departement.setTypeDepartement(TypeDepartement.MEDICAL);
            departement.setDescription("Département de médecine interne");
            departement.setHopital(hopital);
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