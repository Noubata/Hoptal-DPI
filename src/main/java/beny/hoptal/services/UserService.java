package beny.hoptal.services;

import beny.hoptal.data.models.User;
import beny.hoptal.dtos.requests.CreerUserRequest;
import beny.hoptal.dtos.requests.LoginRequest;
import beny.hoptal.dtos.responses.LoginResponse;
import jakarta.transaction.Transactional;

public interface UserService {
    @Transactional
    LoginResponse login(LoginRequest request);

    @Transactional
    User creerUser(CreerUserRequest request);
}
