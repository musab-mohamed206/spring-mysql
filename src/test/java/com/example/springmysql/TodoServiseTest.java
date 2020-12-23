package com.example.springmysql;

import java.util.Arrays;
import java.util.List;

import com.example.springmysql.todo.Todo;
import com.example.springmysql.todo.TodoReposotary;
import com.example.springmysql.todo.TodoServices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
public class TodoServiseTest {

    @MockBean
    private TodoReposotary todoReposotary;

    @Autowired
    private TodoServices todoServices;

    @TestConfiguration
    static class TodoServiceContextConfiguration {
        @Bean
        public TodoServices todoServices(){
            return new TodoServices();
        }
    }
    
    @Test
    public void findAllTodos() {

       Todo todo1 = new Todo(1 , "todo1" , "todo1");
       Todo todo2 = new Todo(2 , "todo2" , "todo2");

       List<Todo> data = Arrays.asList(todo1, todo2);

       given(todoReposotary.findAll()).willReturn(data);

       assertThat(todoServices.findAll())
        .hasSize(2)
        .contains(todo1,todo2);

    }
}
