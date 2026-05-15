package com.philippeslater.portfolio.repository;

import com.philippeslater.portfolio.model.Skill;
import java.util.List;


public interface SkillRepository {
    List<Skill> findAll();
    List<Skill> findByCategory(String category);
    List<String> findDistinctCategories();
}
