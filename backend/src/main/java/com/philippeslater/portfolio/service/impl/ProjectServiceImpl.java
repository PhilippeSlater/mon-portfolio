package com.philippeslater.portfolio.service.impl;

import com.philippeslater.portfolio.exception.ResourceNotFoundException;
import com.philippeslater.portfolio.model.Project;
import com.philippeslater.portfolio.repository.ProjectRepository;
import com.philippeslater.portfolio.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    // Spring IoC injecte l'implémentation concrète via le constructeur
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAll(String category, Boolean featured) {
        if (category != null && !category.isBlank()) {
            return projectRepository.findByCategory(category);
        }
        if (Boolean.TRUE.equals(featured)) {
            return projectRepository.findFeatured();
        }
        return projectRepository.findAll();
    }

    @Override
    public Project getById(int id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Projet introuvable : id=" + id));
    }
}
