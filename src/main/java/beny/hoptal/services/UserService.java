package beny.hoptal.services;

import beny.hoptal.data.models.User;
import beny.hoptal.dtos.requests.ActiverDesactiverUserRequest;
import beny.hoptal.dtos.requests.ChangerMotDePasseRequest;
import beny.hoptal.dtos.requests.CreerUserRequest;
import beny.hoptal.dtos.requests.LoginRequest;
import beny.hoptal.dtos.responses.ActiverDesactiverUserResponse;
import beny.hoptal.dtos.responses.ChangerMotDePasseResponse;
import beny.hoptal.dtos.responses.LoginResponse;
import jakarta.transaction.Transactional;

public interface UserService {
    @Transactional
    LoginResponse login(LoginRequest request);

    @Transactional
    User creerUser(CreerUserRequest request);

    @Transactional
    ChangerMotDePasseResponse changerMotDePasse(Long userId, ChangerMotDePasseRequest request);

    @Transactional
    ActiverDesactiverUserResponse activerDesactiverUser(Long userId, ActiverDesactiverUserRequest request);
}
