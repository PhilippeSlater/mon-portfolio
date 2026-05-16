# Architecture — Portfolio Philippe Slater

## Vue d'ensemble

Application web monolithique déployée sur Render.
Le même processus sert l'API REST (`/api/*`) **et** le frontend statique (`/`).

```
Browser
  │
  ├── GET /          → Spring Boot static resources → index.html
  ├── GET /css/…     → style.css
  ├── GET /js/…      → app.js
  │
  └── GET /api/…     → Spring MVC → Controller → Service → Repository
```

## Couches applicatives (SOLID)

```
┌────────────────────────────────────────────────────────────────┐
│  Controller  (HTTP)                                            │
│  ProjectController · SkillController · HealthController        │
│  └── dépend de : ProjectService (interface)                    │
├────────────────────────────────────────────────────────────────┤
│  Service  (logique métier)                                     │
│  ProjectServiceImpl · SkillServiceImpl                         │
│  └── dépend de : ProjectRepository (interface)                 │
├────────────────────────────────────────────────────────────────┤
│  Repository  (persistance)                                     │
│  InMemoryProjectRepository · InMemorySkillRepository           │
├────────────────────────────────────────────────────────────────┤
│  Model  (POJO)                                                 │
│  Project · Skill                                               │
└────────────────────────────────────────────────────────────────┘
```

## Principes SOLID appliqués

| Principe | Application concrète |
|---|---|
| **S** — Single Responsibility | Chaque classe a un seul rôle : Controller = HTTP, Service = métier, Repository = données |
| **O** — Open/Closed | On peut ajouter une `PostgresProjectRepository` sans modifier `ProjectService` |
| **L** — Liskov Substitution | `InMemoryProjectRepository` est substituable par toute autre implémentation de `ProjectRepository` |
| **I** — Interface Segregation | `ProjectRepository` et `SkillRepository` sont des interfaces distinctes et focalisées |
| **D** — Dependency Inversion | Les controllers et services dépendent d'**interfaces**, Spring IoC injecte les implémentations |

## Injection de dépendances (Spring IoC)

Spring détecte automatiquement les beans via les annotations :
- `@RestController` → bean Controller
- `@Service` → bean Service
- `@Repository` → bean Repository

L'injection se fait par **constructeur** (recommandée pour la testabilité) :

```java
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    // Spring IoC injecte InMemoryProjectRepository ici
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
```

## Endpoints API

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/health` | Statut de l'API |
| GET | `/api/projects` | Tous les projets |
| GET | `/api/projects?category=fullstack` | Filtrer par catégorie |
| GET | `/api/projects?featured=true` | Projets mis en avant |
| GET | `/api/projects/{id}` | Un projet par ID |
| GET | `/api/skills` | Toutes les compétences |
| GET | `/api/skills?category=language` | Filtrer par catégorie |
| GET | `/api/skills/categories` | Liste des catégories |

## Déploiement

```
Backend (Spring Boot JAR)  →  Railway
Frontend (static files)    →  servi par Spring Boot (même déploiement)
```

Pour séparer frontend/backend : copier `src/main/resources/static/` sur Vercel.

## Tests

- **Couche Controller** : `MockMvc` + `@SpringBootTest` (tests d'intégration)
- **Couche Service** : Mockito (tests unitaires purs)
- Lancement : `mvn test` dans `/backend`
