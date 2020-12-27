package com.example.springmysql.todo;

import java.util.List;

import javax.validation.Valid;

import com.example.springmysql.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/todos")
public class TodoController extends BaseController{
    @Autowired
    private TodoServices todoServices;

    @GetMapping("/")
    public ResponseEntity<List<Todo>> lisTodo() {
        List<Todo> res = todoServices.findAllByUserId(getCurrentUser().getId());
        return new ResponseEntity<>(res , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        Todo res = todoServices.getById(id);   
        return new ResponseEntity<>(res , HttpStatus.OK);
    }

    @PostMapping(value="/")
    public ResponseEntity<Todo> inserTodo(@Valid @RequestBody Todo todo) {
        todo.setUserId(getCurrentUser().getId());
        Todo res = todoServices.addTodo(todo);
        return new ResponseEntity<Todo>(res ,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Valid> deleteTodo(@PathVariable Long id) {
        todoServices.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
