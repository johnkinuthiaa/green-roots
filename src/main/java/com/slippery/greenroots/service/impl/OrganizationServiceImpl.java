package com.slippery.greenroots.service.impl;

import com.slippery.greenroots.dto.OrganizationDto;
import com.slippery.greenroots.models.Organization;
import com.slippery.greenroots.repository.OrganizationRepository;
import com.slippery.greenroots.repository.UserRepository;
import com.slippery.greenroots.service.OrganizationService;
import com.slippery.greenroots.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository repository;
    private final UserService userService;
    private final UserRepository userRepository;

    public OrganizationServiceImpl(OrganizationRepository repository, UserService userService, UserRepository userRepository) {
        this.repository = repository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public OrganizationDto createNewOrganization(Organization organization, Long userId) {
        OrganizationDto response =new OrganizationDto();
        var user =userService.findUserById(userId);
        if(user.getStatusCode() !=200){
            response.setMessage(user.getMessage());
            response.setStatusCode(user.getStatusCode());
            return response;
        }
        var existsByName =repository.findAll().stream()
                .filter(organization1 -> organization1.getName().equalsIgnoreCase(organization.getName()))
                .toList();
        if(!existsByName.isEmpty()){
            response.setMessage("Organization with the name "+ organization.getName()+" already exists");
            response.setStatusCode(300);
            return response;
        }

        organization.setProjectsInOrganization(new ArrayList<>());
        organization.setUsersInOrganization(new ArrayList<>());
        organization.setOrganizationCreator(user.getUser());

        var userOrganizations =user.getUser().getOrganizationCreated();
        userOrganizations.add(organization);
        user.getUser().setOrganizationCreated(userOrganizations);
        repository.save(organization);
        userRepository.save(user.getUser());
        response.setMessage("organization created successfully");
        response.setOrganization(organization);
        response.setStatusCode(200);
        return response;
    }

    @Override
    public OrganizationDto updateOrganization(Organization organization, Long userId) {
        return null;
    }

    @Override
    public OrganizationDto findOrganizationById(Long organizationId) {
        OrganizationDto response =new OrganizationDto();
        var organization =repository.findById(organizationId);
        if(organization.isEmpty()){
            response.setMessage("No organization with id"+organizationId+" was found");
            response.setStatusCode(404);
            return response;
        }
        response.setOrganization(organization.get());
        response.setStatusCode(200);
        response.setMessage("Organization with id"+organizationId+" .");
        return response;
    }

    @Override
    public OrganizationDto deleteOrganizationById(Long organizationId,Long creatorId) {
        OrganizationDto response=new OrganizationDto();
        var existingOrganization =findOrganizationById(organizationId);
        var existingUser =userService.findUserById(creatorId);
        if(existingUser.getStatusCode() !=200){
            response.setMessage(existingUser.getMessage());
            response.setStatusCode(existingUser.getStatusCode());
            return response;
        }
        if(existingOrganization.getStatusCode() !=200){
            return existingOrganization;
        }
        var organization =existingOrganization.getOrganization();
        if(!Objects.equals(organization.getOrganizationCreator().getId(), existingUser.getUser().getId())){
            response.setMessage("This org does not belong to the user ");
            response.setStatusCode(401);
            return response;
        }
        organization.setOrganizationCreator(null);
        organization.setProjectsInOrganization(null);
        organization.setUsersInOrganization(null);

        List<Organization> organizations =existingUser.getUser().getOrganizationCreated();
        organizations.remove(organization);
        existingUser.getUser().setOrganizationCreated(organizations);

        repository.delete(organization);
        userRepository.save(existingUser.getUser());
        response.setMessage("Organization deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public OrganizationDto deleteAll() {
        return null;
    }
}
