package beny.hoptal.services;

import beny.hoptal.Security.JwtService;
import beny.hoptal.data.models.Role;
import beny.hoptal.data.models.User;
import beny.hoptal.data.repositories.RoleRepository;
import beny.hoptal.data.repositories.UserRepository;
import beny.hoptal.dtos.requests.ActiverDesactiverUserRequest;
import beny.hoptal.dtos.requests.ChangerMotDePasseRequest;
import beny.hoptal.dtos.requests.CreerUserRequest;
import beny.hoptal.dtos.requests.LoginRequest;
import beny.hoptal.dtos.responses.ActiverDesactiverUserResponse;
import beny.hoptal.dtos.responses.ChangerMotDePasseResponse;
import beny.hoptal.dtos.responses.LoginResponse;
import beny.hoptal.exceptions.*;
import beny.hoptal.utils.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByNomUtilisateur(request.getNomUtilisateur())
                .orElseThrow(() -> new UtilisateurIntrouvableEception("Utilisateur introuvable."));

        if (!user.getActif()) {
            throw new CompteDesactiveException("Ce compte est désactivé. Contactez l'administrateur.");
        }

        if (!passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasseHashe())) {
            throw new MotDePasseIncorrectException("Mot de passe incorrect.");
        }
        String token = jwtService.generateToken(user);
        LoginResponse response = new LoginResponse();;
        response.setToken(token);
        response.setUserId(user.getId());
        response.setNomUtilisateur(user.getNomUtilisateur());
        response.setRole(user.getRole().getNom());

        return response;
    }

    @Transactional
    @Override
    public User creerUser(CreerUserRequest request) {
        if (userRepository.findByNomUtilisateur(request.getNomUtilisateur()).isPresent()) {
            throw new UtilisateurExisteDejaException("Le nom d'utilisateur '"
                    + request.getNomUtilisateur() + "' est déjà utilisé.");
        }
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RoleIntrouvableException("Rôle introuvable."));

        User user = new User();
        user.setNomUtilisateur(request.getNomUtilisateur());
        user.setMotDePasseHashe(passwordEncoder.encode(request.getMotDePasse()));
        user.setRole(role);
        user.setActif(true);
        user.setDateCreation(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public ChangerMotDePasseResponse changerMotDePasse(Long userId, ChangerMotDePasseRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UtilisateurIntrouvableEception("Utilisateur introuvable."));

        if (!passwordEncoder.matches(request.getAncienMotDePasse(), user.getMotDePasseHashe())) {
            throw new AncienMotDePasseIncorrectException("Ancien mot de passe incorrect.");
        }

        user.setMotDePasseHashe(passwordEncoder.encode(request.getNouveauMotDePasse()));
        userRepository.save(user);
        return new ChangerMotDePasseResponse("Mot de passe modifié avec succès.");
    }

    @Transactional
    @Override
    public ActiverDesactiverUserResponse activerDesactiverUser(Long userId, ActiverDesactiverUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UtilisateurIntrouvableEception("Utilisateur introuvable."));
        user.setActif(request.getActif());
        userRepository.save(user);
        return UserMapper.toActiverDesactiverUserResponse(user);
    }
}
