package com.philippeslater.portfolio.repository;

import com.philippeslater.portfolio.model.Project;
import java.util.List;
import java.util.Optional;


public interface ProjectRepository {
    List<Project> findAll();
    Optional<Project> findById(int id);
    List<Project> findByCategory(String category);
    List<Project> findFeatured();
}
