package com.philippeslater.portfolio.controller;

import com.philippeslater.portfolio.model.Project;
import com.philippeslater.portfolio.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean featured) {
        return ResponseEntity.ok(projectService.getAll(category, featured));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getById(id));
    }
}
