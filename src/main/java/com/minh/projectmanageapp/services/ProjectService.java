package com.minh.projectmanageapp.services;

import com.minh.projectmanageapp.domain.Project;
import com.minh.projectmanageapp.exceptions.ProjectIdException;
import com.minh.projectmanageapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier((project.getProjectIdentifier()).toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID exists: " + project.getProjectIdentifier().toUpperCase());
        }
    }

    public Project updateProject(Project project) {

        Project existedProject = findProjectByIdentifier(project.getProjectIdentifier());
//        existedProject.setProjectName(project.getProjectName());
//        existedProject.setDescription(project.getDescription());
        project.setId(existedProject.getId());
        project.setCreatedAt((existedProject.getCreatedAt()));
        return projectRepository.save(project);
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null)
            throw new ProjectIdException("Project ID '" + projectId + "' not exists");

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = findProjectByIdentifier(projectId);
        if (project == null)
            throw new ProjectIdException("Project ID '" + projectId + "' not exists");

        projectRepository.delete(project);

    }

}
