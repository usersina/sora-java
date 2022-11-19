package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Designation;
import application.hibernate.repos.DesignationRepository;

public class DesignationServiceImpl implements DesignationService {
    DesignationRepository designationRepository = new DesignationRepository();

    @Override
    public Designation saveDesignation(Designation designation) {
        return designationRepository.save(designation);
    }

    @Override
    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

}
