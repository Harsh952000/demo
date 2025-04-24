package com.lcwd.todo.Todo_Application.dao;

import com.lcwd.todo.Todo_Application.helper.Helper;
import com.lcwd.todo.Todo_Application.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo>
{

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo=new Todo();
        todo.setId(rs.getInt("id"));
        todo.setTitle(rs.getString("title"));
        todo.setStatus(rs.getString("status"));
        todo.setContent(rs.getString("content"));
        todo.setAddedDate(rs.getTimestamp("addedDate"));
        todo.setTodoDate(rs.getTimestamp("todoDate"));
        return todo;
    }
}
