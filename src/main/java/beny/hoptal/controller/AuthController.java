package beny.hoptal.controller;

import beny.hoptal.dtos.requests.LoginRequest;
import beny.hoptal.dtos.requests.ChangerMotDePasseRequest;
import beny.hoptal.dtos.requests.ActiverDesactiverUserRequest;
import beny.hoptal.dtos.responses.APIResponse;
import beny.hoptal.dtos.responses.LoginResponse;
import beny.hoptal.dtos.responses.ActiverDesactiverUserResponse;
import beny.hoptal.dtos.responses.ChangerMotDePasseResponse;
import beny.hoptal.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, loginResponse));
    }

    @PutMapping("/changer-mot-de-passe/{userId}")
    public ResponseEntity<APIResponse<ChangerMotDePasseResponse>> changerMotDePasse(@PathVariable Long userId, @RequestBody ChangerMotDePasseRequest request) {
        ChangerMotDePasseResponse changeMotDePasseResponse = userService.changerMotDePasse(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, changeMotDePasseResponse));
    }

    @PutMapping("/activer-desactiver/{userId}")
    public ResponseEntity<APIResponse<ActiverDesactiverUserResponse>> activerDesactiverUser(
            @PathVariable Long userId,
            @RequestBody ActiverDesactiverUserRequest request) {
        ActiverDesactiverUserResponse activerDesactiverUserResponse = userService.activerDesactiverUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<>(true, activerDesactiverUserResponse));
    }
}