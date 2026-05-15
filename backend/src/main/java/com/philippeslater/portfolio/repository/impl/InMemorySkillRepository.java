package com.philippeslater.portfolio.repository.impl;

import com.philippeslater.portfolio.model.Skill;
import com.philippeslater.portfolio.repository.SkillRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemorySkillRepository implements SkillRepository {

    private static final List<Skill> DATA = List.of(
        new Skill(1,  "Java",         "language",    "expert"),
        new Skill(2,  "C#",           "language",    "expert"),
        new Skill(3,  "Python",       "language",    "avancé"),
        new Skill(4,  "JavaScript",   "language",    "avancé"),
        new Skill(5,  "C/C++",        "language",    "avancé"),
        new Skill(6,  "Kotlin",       "language",    "intermédiaire"),
        new Skill(7,  "SQL",          "language",    "avancé"),
        new Skill(8,  "Spring Boot",  "backend",     "avancé"),
        new Skill(9,  "Node.js",      "backend",     "avancé"),
        new Skill(10, "REST API",     "backend",     "expert"),
        new Skill(11, "Socket.IO",    "backend",     "intermédiaire"),
        new Skill(12, "JavaEE / JSP", "backend",     "avancé"),
        new Skill(13, "HTML/CSS/JS",  "frontend",    "intermédiaire"),
        new Skill(14, "React",        "frontend",    "intermédiaire"),
        new Skill(15, "MySQL",        "database",    "avancé"),
        new Skill(16, "SQL Server",   "database",    "avancé"),
        new Skill(17, "Docker",       "devops",      "intermédiaire"),
        new Skill(18, "Git",          "devops",      "avancé"),
        new Skill(19, "FreeRTOS",     "embedded",    "intermédiaire"),
        new Skill(20, "STM32",        "embedded",    "intermédiaire"),
        new Skill(21, "Raspberry Pi", "embedded",    "intermédiaire"),
        new Skill(22, "Agile/Scrum",  "methodology", "avancé"),
        new Skill(23, "TDD / JUnit",  "methodology", "avancé")
    );

    @Override
    public List<Skill> findAll() {
        return DATA;
    }

    @Override
    public List<Skill> findByCategory(String category) {
        return DATA.stream().filter(s -> s.getCategory().equalsIgnoreCase(category)).toList();
    }

    @Override
    public List<String> findDistinctCategories() {
        return DATA.stream().map(Skill::getCategory).distinct().toList();
    }
}
