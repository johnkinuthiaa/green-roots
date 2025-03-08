package com.slippery.greenroots.service.impl;

import com.slippery.greenroots.dto.ProjectDto;
import com.slippery.greenroots.models.Project;
import com.slippery.greenroots.repository.ProjectRepository;
import com.slippery.greenroots.service.ProjectService;
import com.slippery.greenroots.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectServiceImplementation implements ProjectService {
    private final ProjectRepository repository;
    private final UserService userService;

    public ProjectServiceImplementation(ProjectRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public ProjectDto createNewProject(Project projectDetails,Long userId) {
        ProjectDto response =new ProjectDto();
        var project =findProjectByName(projectDetails.getName());
        var user =userService.findUserById(userId);
        if(user.getStatusCode() !=200){
            response.setMessage(user.getMessage());
            response.setStatusCode(user.getStatusCode());
            return response;
        }
        if(project.getStatusCode() ==200){
            response.setStatusCode(401);
            response.setMessage("Project with that name already exists");
            return response;
        }
        projectDetails.setCreatedOn(LocalDateTime.now());
        projectDetails.setCreatedBy(user.getUser());
        repository.save(projectDetails);
        response.setStatusCode(200);
        response.setMessage("New project created");
        response.setProject(projectDetails);
        return response;
    }

    @Override
    public ProjectDto updateProject(Project projectDetails, Long projectId,Long userId) {
        ProjectDto response =new ProjectDto();

        return null;
    }

    @Override
    public ProjectDto findProjectById(Long projectId) {
        ProjectDto response =new ProjectDto();
        Optional<Project> project =repository.findById(projectId);
        if(project.isEmpty()){
            response.setMessage("Project not found");
            response.setStatusCode(404);
            return response;
        }
        response.setMessage("Project with id"+projectId);
        response.setStatusCode(200);
        response.setProject(project.get());
        return response;
    }

    @Override
    public ProjectDto findProjectByName(String projectName) {
        ProjectDto response =new ProjectDto();
        List<Project> projectByName =repository.findAll()
                .stream().filter(project -> project.getName().equalsIgnoreCase(projectName))
                .toList();
        if(projectByName.isEmpty()){
            response.setMessage("project with name"+projectName+" not found!");
            response.setStatusCode(404);
            return response;
        }
        response.setProject(projectByName.get(0));
        response.setStatusCode(200);
        response.setMessage("Project "+projectName);
        return response;
    }

    @Override
    public ProjectDto deleteProjectById(Long userId, Long projectId) {
        ProjectDto response =new ProjectDto();
        var existingProject =findProjectById(projectId);
        var user =userService.findUserById(userId);
        if(user.getStatusCode() !=200){
            response.setMessage(user.getMessage());
            response.setStatusCode(user.getStatusCode());
            return response;
        }
        if(existingProject.getStatusCode() !=200){
            return existingProject;
        }
        if(!Objects.equals(user.getUser().getId(), existingProject.getProject().getCreatedBy().getId())){
            response.setMessage("Project does not belong to this user");
            response.setStatusCode(401);
            return response;
        }

        repository.delete(existingProject.getProject());
        response.setMessage("Project deleted successfully");
        response.setStatusCode(200);
        return response;
    }
}
