package com.slippery.greenroots.repository;

import com.slippery.greenroots.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByName(String name);
}
