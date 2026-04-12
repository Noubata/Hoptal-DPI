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
    @ExceptionHandler(ServiceIntrouvableException.class)
    public ResponseEntity<String> handleServiceIntrouvable(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(RoleLaborantinIntrouvableException.class)
    public ResponseEntity<String> handleRoleLaborantinIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(LaborantinIntrouvableException.class)
    public ResponseEntity<String> handleLaborantinIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(DemandeAnalyseIntrouvable.class)
    public ResponseEntity<String> handleDemandeAnalyse(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(ResultatDejaSaisi.class)
    public ResponseEntity<String> handleResultatDejaSaisi(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(PatientIntrouvable.class)
    public ResponseEntity<String> handlePatientIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(CompteDesactiveException.class)
    public ResponseEntity<String> handleCompteDesactiveException(Exception error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }
    @ExceptionHandler(NumeroLicenceExisteDeja.class)
    public ResponseEntity<String> handleNumeroLicenceExisteDeja(Exception error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
    }
    @ExceptionHandler(SpecialiteIntrouvable.class)
    public ResponseEntity<String> handleSpecialiteIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(DepartementIntrouvableException.class)
    public ResponseEntity<String> handleDepartementIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(RoleDocteurIntrouvableException.class)
    public ResponseEntity<String> handleRoleDocteurIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(MedecinIntrouvableException.class)
    public ResponseEntity<String> handleMedecinIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(ReleveIntrouvableException.class)
    public ResponseEntity<String> handleReleveIntrouvableException(Exception error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }
    @ExceptionHandler(AllergieDecterException.class)
    public ResponseEntity<String> handleAllergieDecterException(Exception error) {
        return ResponseEntity.status(HttpStatus.FOUND).body(error.getMessage());
    }
}
