# Portfolio — Philippe Slater

Portfolio web professionnel avec API REST en **Java Spring Boot**, déployé sur Railway.

**Demo :** _[à remplir après déploiement]_  
**API :** `GET /api/projects` · `GET /api/skills`

---

## Stack technique

| Couche | Technologie | Version |
|---|---|---|
| Backend | Java + Spring Boot | 21 / 3.3 |
| IoC / DI | Spring Framework | 6.x |
| Build | Maven | 3.9+ |
| Frontend | HTML5 + CSS3 + JavaScript vanilla | — |
| Tests | JUnit 5 + MockMvc + Mockito | 5.x |
| Hébergement | Railway (JAR Spring Boot) | — |

---

## Architecture

L'application respecte les principes **SOLID** et l'injection de dépendances Spring IoC :

```
Controller → Service (interface) → Repository (interface) → données en mémoire
```

Voir [docs/architecture.md](docs/architecture.md) pour le détail complet.

---

## Lancer localement

### Prérequis
- Java 21+
- Maven 3.9+

### Installation

```bash
git clone https://github.com/philippe-slater/mon-portfolio.git
cd mon-portfolio/backend
mvn spring-boot:run
```

L'application démarre sur **http://localhost:3001**

- Portfolio : http://localhost:3001
- API projets : http://localhost:3001/api/projects
- API compétences : http://localhost:3001/api/skills
- Health : http://localhost:3001/api/health

### Variables d'environnement

Voir [.env.example](.env.example).

| Variable | Défaut | Description |
|---|---|---|
| `PORT` | `3001` | Port du serveur Spring Boot |

---

## Tests

```bash
cd backend
mvn test
```

11 tests couvrant :
- `ProjectControllerTest` — MockMvc (GET all, filtres, GET by id, 404)
- `SkillControllerTest` — MockMvc (GET all, filtre catégorie, GET categories)
- `ProjectServiceTest` — JUnit + Mockito (logique métier, exceptions)

---

## Déploiement (Railway)

1. Créer un compte sur [railway.app](https://railway.app)
2. **New Project → Deploy from GitHub repo** → sélectionner ce repo
3. Définir le **Root Directory** : `backend`
4. Ajouter la variable d'environnement `PORT=8080` (Railway gère le port automatiquement)
5. Railway génère une URL publique — l'ajouter dans le README

---

## Projets mis en avant

| Projet | Stack | Demo |
|---|---|---|
| Gestionnaire Kanban collaboratif | Node.js · Express · Socket.IO | — |
| Calculatrice avec arbre d'évaluation | React · C# · REST API | [calculatrice-ui.onrender.com](https://calculatrice-ui.onrender.com/) |
| Gestion de tournois de soccer | Kotlin · Android SDK | Production (AQBB) |
| Portfolio API (ce projet) | Java 21 · Spring Boot 3 · Maven | Ce site |

---

## Ce que j'ai appris

1. **SOLID en pratique** — Séparer les couches par interfaces plutôt que par implémentations facilite les tests (Mockito peut substituer n'importe quel repository) et l'évolution (swap InMemory → JPA sans toucher les services).
2. **Spring IoC par constructeur** — L'injection par constructeur (vs `@Autowired` sur champ) rend les dépendances explicites et les classes testables sans conteneur Spring.
3. **MockMvc vs tests unitaires** — MockMvc teste la couche HTTP complète (sérialisation JSON, codes de statut, routing) ; Mockito teste la logique métier en isolation. Les deux sont complémentaires.

---

## Prochaines étapes

- [ ] Remplacer `InMemoryRepository` par JPA + PostgreSQL
- [ ] Ajouter un endpoint `POST /api/contact` (formulaire de contact)
- [ ] Intégrer GitHub Actions CI (build + tests automatiques)
- [ ] Ajouter Swagger/OpenAPI pour documenter l'API

---

Philippe Slater · [philippe.slater@gmail.com](mailto:philippe.slater@gmail.com) · [LinkedIn](https://linkedin.com/in/philippe-slater)
