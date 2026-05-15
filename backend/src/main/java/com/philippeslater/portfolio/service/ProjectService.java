package com.philippeslater.portfolio.service;

import com.philippeslater.portfolio.model.Project;
import java.util.List;


public interface ProjectService {
    List<Project> getAll(String category, Boolean featured);
    Project getById(int id);
}
