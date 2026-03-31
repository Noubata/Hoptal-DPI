package beny.hoptal.utils;

import beny.hoptal.data.models.User;
import beny.hoptal.dtos.responses.ActiverDesactiverUserResponse;

public class UserMapper {

    public static ActiverDesactiverUserResponse toActiverDesactiverUserResponse(User user) {
        ActiverDesactiverUserResponse response = new ActiverDesactiverUserResponse();
        response.setId(user.getId());
        response.setNomUtilisateur(user.getNomUtilisateur());
        response.setActif(user.getActif());
        response.setDateCreation(user.getDateCreation());
        response.setRole(user.getRole().getNom());
        return response;
    }
}
