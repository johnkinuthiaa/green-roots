package com.slippery.greenroots.service;

import com.slippery.greenroots.dto.ProjectDto;
import com.slippery.greenroots.models.Project;

public interface ProjectService {
    ProjectDto createNewProject(Project projectDetails);
    ProjectDto updateProject(Project projectDetails,Long projectId);
    ProjectDto findProjectById(Long projectId);
    ProjectDto findProjectByName(String projectName);
    ProjectDto deleteProjectById(Long userId,Long projectId);
}
