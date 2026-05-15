package com.philippeslater.portfolio.model;

import java.util.List;

public class Project {

    private int id;
    private String title;
    private String description;
    private List<String> tech;
    private String category;
    private String github;
    private String demo;
    private List<String> highlights;
    private int year;
    private boolean featured;

    public Project() {}

    public Project(int id, String title, String description, List<String> tech,
                   String category, String github, String demo,
                   List<String> highlights, int year, boolean featured) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tech = tech;
        this.category = category;
        this.github = github;
        this.demo = demo;
        this.highlights = highlights;
        this.year = year;
        this.featured = featured;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<String> getTech() { return tech; }
    public String getCategory() { return category; }
    public String getGithub() { return github; }
    public String getDemo() { return demo; }
    public List<String> getHighlights() { return highlights; }
    public int getYear() { return year; }
    public boolean isFeatured() { return featured; }
}
