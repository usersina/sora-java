package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Designation;

public interface DesignationService {
    Designation saveDesignation(Designation designation);

    List<Designation> getAllDesignations();
}
