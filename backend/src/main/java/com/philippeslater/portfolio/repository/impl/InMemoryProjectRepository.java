package com.philippeslater.portfolio.repository.impl;

import com.philippeslater.portfolio.model.Project;
import com.philippeslater.portfolio.repository.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProjectRepository implements ProjectRepository {

    private static final List<Project> DATA = List.of(
        new Project(2,
            "Calculatrice avec arbre d'évaluation",
            "Calculatrice full-stack qui visualise l'arbre d'évaluation des expressions mathématiques pour illustrer l'ordre de priorité des opérateurs.",
            List.of("React", "C#", "REST API", "Render"),
            "fullstack", "https://github.com/philippe-slater/calculatrice", "https://calculatrice-ui.onrender.com/",
            List.of("Visualisation de l'arbre syntaxique en temps réel", "Backend C# exposé via une API REST", "Déployé sur Render"),
            2025, true),
        new Project(3,
            "Application de gestion de tournois de soccer",
            "Application Android native pour gérer les tournois de soccer sur table. Déployée et activement utilisée par l'AQBB (Association québécoise de babyfoot).",
            List.of("Kotlin", "Android SDK"),
            "mobile", null, null,
            List.of("Déployée en production, utilisée par une vraie association", "Gestion complète tournois, équipes et résultats", "Interface mobile native Android"),
            2024, true),
        new Project(4,
            "Portfolio API — Java Spring Boot",
            "Ce portfolio lui-même : API REST construite avec Java Spring Boot, principes SOLID et injection de dépendances Spring IoC.",
            List.of("Java 21", "Spring Boot 3", "Spring IoC", "Maven", "JUnit 5"),
            "fullstack", "https://github.com/philippe-slater/mon-portfolio", "https://mon-portfolio-ui.onrender.com/",
            List.of("Architecture en couches : Controller → Service → Repository", "Principes SOLID appliqués (IoC, interfaces, SRP)", "Tests MockMvc et JUnit 5"),
            2025, true),
        new Project(5,
            "Système de caméra HDR grand angle",
            "Système de capture HDR à deux caméras avec Raspberry Pi 5, contrôle Python via SDK Spinnaker. Projet de fin d'études, génie informatique.",
            List.of("Python", "Raspberry Pi 5", "Spinnaker SDK", "C/C++"),
            "embedded", null, null,
            List.of("Capture HDR double caméra synchronisée", "Interface graphique Python temps réel", "Optimisation RAM/NVMe et transfert USB"),
            2025, false),
        new Project(6,
            "Système de grue automatisée",
            "Contrôle de grue automatisé sur microcontrôleur STM32 avec architecture multitâche FreeRTOS et interface Python de surveillance.",
            List.of("STM32", "C/C++", "FreeRTOS", "Python", "UART"),
            "embedded", null, null,
            List.of("Architecture multitâche temps réel FreeRTOS", "Communication UART précise", "Interface Python de supervision"),
            2024, false)
    );

    @Override
    public List<Project> findAll() {
        return DATA;
    }

    @Override
    public Optional<Project> findById(int id) {
        return DATA.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Project> findByCategory(String category) {
        return DATA.stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).toList();
    }

    @Override
    public List<Project> findFeatured() {
        return DATA.stream().filter(Project::isFeatured).toList();
    }
}
