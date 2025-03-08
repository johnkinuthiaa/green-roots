package com.slippery.greenroots.controller;

import com.slippery.greenroots.dto.ProjectDto;
import com.slippery.greenroots.models.Project;
import com.slippery.greenroots.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController  {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<ProjectDto> createNewProject(@RequestBody Project projectDetails,@RequestParam Long userId) {
        return ResponseEntity.ok(service.createNewProject(projectDetails,userId));
    }
    @PutMapping("/update")
    public ResponseEntity<ProjectDto> updateProject(@RequestBody Project projectDetails,@RequestParam Long projectId,@RequestParam Long userId) {
        return ResponseEntity.ok(service.updateProject(projectDetails, projectId,userId));
    }
    @GetMapping("/{projectId}/get")
    public ResponseEntity<ProjectDto> findProjectById(@PathVariable Long projectId) {
        return ResponseEntity.ok(service.findProjectById(projectId));
    }
    @GetMapping("/{projectName}/get")
    public ResponseEntity<ProjectDto> findProjectByName(@PathVariable String projectName) {
        return ResponseEntity.ok(service.findProjectByName(projectName));
    }
    @DeleteMapping("/{projectId}/delete")
    public ResponseEntity<ProjectDto> deleteProjectById(@RequestParam Long userId,@PathVariable Long projectId) {
        return ResponseEntity.ok(service.deleteProjectById(userId, projectId));
    }
}
