package com.example.springmysql.todo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;


@Entity
public class Todo {

    @Id
    @AutoConfigureOrder
    private Long id;

    @NotNull(message = "Titel is required")
    @Size(min = 3, message = "Title must be at least 3 characers long")
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    public Todo(int i, String string, String string2) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }
}
