package com.lcwd.todo.Todo_Application.services;

import com.lcwd.todo.Todo_Application.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoServices {

    Logger logger=LoggerFactory.getLogger(TodoServices.class);

    List<Todo> todos=new ArrayList<>();
    public Todo createTodo(Todo todo)
    {
        todos.add(todo);
        logger.info("created new todo");
        return todo;
    }
    public List<Todo> getAllTodos()
    {
        logger.info("returnall todo");
        return todos;

    }
    public Todo getSingleTodo(int id)
    {
        for(Todo todo:todos)
        {
            if(todo.getId()==id) {
                logger.info("get todo according to id");
                return todo;
            }
        }
        return null;
    }
    public Todo updateTodo(Todo updateThisTodo,int id)
    {
        for(Todo todo:todos)
        {
            if(todo.getId()==id)
            {
                todo=updateThisTodo;
                todo.setId(id);
                return  todo;
            }
        }
        return null;
    }

    public String deleteTodo(int id)
    {
        List<Todo> newTodo=new ArrayList<>();
        boolean flag =false;
        for(Todo todo:todos)
        {
            if(todo.getId()!=id) {
                newTodo.add(todo);
            }
            else flag =true;
        }
        todos=newTodo;
        if(flag)
            return "todo deleted";
        else    return "wrongId";
    }
}
