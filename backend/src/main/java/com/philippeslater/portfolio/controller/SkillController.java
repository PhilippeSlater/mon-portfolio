package com.philippeslater.portfolio.controller;

import com.philippeslater.portfolio.model.Skill;
import com.philippeslater.portfolio.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAll(
            @RequestParam(required = false) String category) {
        return ResponseEntity.ok(skillService.getAll(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(skillService.getCategories());
    }
}
