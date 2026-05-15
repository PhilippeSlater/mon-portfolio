package com.philippeslater.portfolio.service.impl;

import com.philippeslater.portfolio.model.Skill;
import com.philippeslater.portfolio.repository.SkillRepository;
import com.philippeslater.portfolio.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAll(String category) {
        if (category != null && !category.isBlank()) {
            return skillRepository.findByCategory(category);
        }
        return skillRepository.findAll();
    }

    @Override
    public List<String> getCategories() {
        return skillRepository.findDistinctCategories();
    }
}
