package com.philippeslater.portfolio.model;

public class Skill {

    private int id;
    private String name;
    private String category;
    private String level;

    public Skill() {}

    public Skill(int id, String name, String category, String level) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.level = level;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getLevel() { return level; }
}
