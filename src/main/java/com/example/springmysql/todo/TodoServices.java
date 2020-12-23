package com.example.springmysql.todo;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.springmysql.error.ConflictException;
import com.example.springmysql.error.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServices {
    @Autowired
    private TodoReposotary todoReposotary;

	public List<Todo> findAll() {
		return todoReposotary.findAll();
	}

	public Todo getById(Long id) {
		try {
			return todoReposotary.findById(id).get();
		} catch (NoSuchElementException ex) {
			//TODO: handle exception
			throw new NotFoundException(String.format("Now Record whit the id [%s] was found in our database", id));
		}
	}

	public Todo addTodo(Todo todo) {
		if (todoReposotary.findByTitle(todo.gettitle()) != null) {
			throw new ConflictException("Another record with the same title exists");
		}
		return todoReposotary.save(todo);
	}

	public void delete(Long id) {
		todoReposotary.deleteById(id);
	}
}
