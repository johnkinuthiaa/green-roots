package com.slippery.greenroots.service.impl;

import com.slippery.greenroots.dto.ProjectDto;
import com.slippery.greenroots.models.Project;
import com.slippery.greenroots.repository.ProjectRepository;
import com.slippery.greenroots.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImplementation implements ProjectService {
    private final ProjectRepository repository;

    public ProjectServiceImplementation(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectDto createNewProject(Project projectDetails) {
        return null;
    }

    @Override
    public ProjectDto updateProject(Project projectDetails, Long projectId) {
        return null;
    }

    @Override
    public ProjectDto findProjectById(Long projectId) {
        return null;
    }

    @Override
    public ProjectDto findProjectByName(String projectName) {
        return null;
    }

    @Override
    public ProjectDto findDeleteProjectById(Long userId, Long projectId) {
        return null;
    }
}
