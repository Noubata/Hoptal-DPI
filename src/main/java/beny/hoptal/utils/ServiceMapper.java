package beny.hoptal.utils;

import beny.hoptal.data.models.Service;
import beny.hoptal.dtos.responses.ServiceResponse;

public class ServiceMapper {

    public static ServiceResponse toResponse(Service service) {
        ServiceResponse response = new ServiceResponse();
        response.setId(service.getId());
        response.setNom(service.getNom());
        response.setDescription(service.getDescription());
        response.setTypeService(service.getTypeService());
        response.setEtage(service.getEtage());
        response.setDepartementId(
                service.getDepartement() != null ? service.getDepartement().getId() : null
        );
        return response;
    }
}