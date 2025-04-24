package com.lcwd.todo.Todo_Application;

import com.lcwd.todo.Todo_Application.dao.TodoDao;
import com.lcwd.todo.Todo_Application.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	TodoDao todoDao;
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*	Todo todo=new Todo();
	todo.setId(2);
	todo.setTitle("har");
	todo.setContent("harsh");
	todo.setStatus("harsh");
	todo.setTodoDate(new Date());
	todo.setAddedDate(new Date());
	todoDao.saveTodo(todo);*/
System.out.println(todoDao.getTodo(2))	;
List<Todo> todos=todoDao.getAllTodos();
for(Todo t:todos)
{
	System.out.println(t);
}


	}
}
