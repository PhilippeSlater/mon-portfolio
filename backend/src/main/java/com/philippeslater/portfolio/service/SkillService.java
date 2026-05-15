package com.philippeslater.portfolio.service;

import com.philippeslater.portfolio.model.Skill;
import java.util.List;


public interface SkillService {
    List<Skill> getAll(String category);
    List<String> getCategories();
}
