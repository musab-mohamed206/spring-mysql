package com.example.springmysql.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoReposotary extends JpaRepository<Todo, Long> {
    Todo findByTitle(String title);
    List<Todo> findAllByUserId(Long userId);
}
