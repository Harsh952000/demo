package com.lcwd.todo.Todo_Application.controllers;

import com.lcwd.todo.Todo_Application.models.Todo;
import com.lcwd.todo.Todo_Application.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoServices services;

    //create todo
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo)
    {
        if(todo.getId()==0)
        {
            todo.setId(new Random().nextInt(99));
        }
        //create date at the time of todo is added
        Date newDate=new Date();
        todo.setAddedDate(newDate);

        Todo todo1=services.createTodo(todo);
      return new ResponseEntity<>(todo1,HttpStatus.CREATED);
    }

    //get all todo
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler()
    {
        return new ResponseEntity<>(services.getAllTodos(),HttpStatus.OK);
    }

    //get single todo
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId)
    {
        Todo todo=services.getSingleTodo(todoId);
        if(todo!=null)
            return new ResponseEntity<>(todo,HttpStatus.OK);
        else
            return new ResponseEntity<>(todo,HttpStatus.BAD_REQUEST);



    }

    //update todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails,@PathVariable int id)
    {
       Todo todo= services.updateTodo(todoWithNewDetails,id);
       if(todo!=null)
       {
           return new ResponseEntity<>(todo,HttpStatus.CREATED);
       }else return new ResponseEntity<>(todo,HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int id)
    {
        String response=services.deleteTodo(id);
        if(response.equals("wrongId"))
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
