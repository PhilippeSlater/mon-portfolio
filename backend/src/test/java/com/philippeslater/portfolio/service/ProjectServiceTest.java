package com.philippeslater.portfolio.service;

import com.philippeslater.portfolio.exception.ResourceNotFoundException;
import com.philippeslater.portfolio.model.Project;
import com.philippeslater.portfolio.repository.ProjectRepository;
import com.philippeslater.portfolio.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    private ProjectRepository projectRepository;
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectRepository = mock(ProjectRepository.class);
        projectService = new ProjectServiceImpl(projectRepository);
    }

    @Test
    void getAll_sansFiltre_appelleFindAll() {
        when(projectRepository.findAll()).thenReturn(List.of());
        projectService.getAll(null, null);
        verify(projectRepository).findAll();
    }

    @Test
    void getAll_avecCategorie_appelleFindByCategory() {
        when(projectRepository.findByCategory("fullstack")).thenReturn(List.of());
        projectService.getAll("fullstack", null);
        verify(projectRepository).findByCategory("fullstack");
    }

    @Test
    void getAll_featuredTrue_appelleFindFeatured() {
        when(projectRepository.findFeatured()).thenReturn(List.of());
        projectService.getAll(null, true);
        verify(projectRepository).findFeatured();
    }

    @Test
    void getById_idValide_retourneProjet() {
        Project projet = new Project(1, "Test", "desc", List.of(), "fullstack", null, null, List.of(), 2025, true);
        when(projectRepository.findById(1)).thenReturn(Optional.of(projet));
        Project result = projectService.getById(1);
        assertThat(result.getId()).isEqualTo(1);
    }

    @Test
    void getById_idInexistant_leveException() {
        when(projectRepository.findById(999)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> projectService.getById(999))
            .isInstanceOf(ResourceNotFoundException.class);
    }
}
