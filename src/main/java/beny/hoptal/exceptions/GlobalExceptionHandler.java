package beny.hoptal.exceptions;

import beny.hoptal.dtos.responses.ActiverDesactiverUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AncienMotDePasseIncorrectException.class)
    public ResponseEntity<String> handleAncienMotDePasseException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(MotDePasseIncorrectException.class)
    public ResponseEntity<String> handleMotDePasseException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(RoleIntrouvableException.class)
    public ResponseEntity<String> handleRoleIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(UtilisateurExisteDejaException.class)
    public ResponseEntity<String> handleUtilisateurExisteDejaException(Exception error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
    }
    @ExceptionHandler(UtilisateurIntrouvableEception.class)
    public ResponseEntity<String> handleUtilisateurIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }

}
