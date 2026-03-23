package beny.hoptal.data.models;

import java.util.List;

public class Departement {
    private Long id;
    private String nom;
    private TypeDepartement typeDepartement;
    private Hopital hopital;
    private String description;
    private List<Service> services;

}
