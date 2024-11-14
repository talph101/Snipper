package com.snipper.Snipper.Snippets.entity;


import jakarta.persistence.*;

@Entity
@Table
public class Snippets {
    @Id
    private Long id;
    private String language;
    private String code;

    public Snippets() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
