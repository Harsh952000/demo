package com.lcwd.todo.Todo_Application.dao;

import com.lcwd.todo.Todo_Application.helper.Helper;
import com.lcwd.todo.Todo_Application.models.Todo;
import org.slf4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.chrono.JapaneseDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);
    JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;
        String createTable = "create table IF NOT EXISTS todos(id int primary key,title varchar(100) not null,content varchar(500),status varchar(10) not null,addedDate datetime,todoDate datetime)";
        template.update(createTable);
        logger.info("todo table created");
    }


    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public void saveTodo(Todo todo) {
        String insertQuery = "INSERT INTO todos (id, title, content, status, addedDate,todoDate) VALUES (?,?,?,?,?,?)";


        template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getTodoDate());

    }

    public Todo getTodo(int id) throws ParseException {
        String query = "Select * from todos where id=?";
/*        Map<String, Object> map = template.queryForMap(query, id);
        Todo todo = new Todo();
        todo.setId((int) map.get("id"));
        todo.setTitle((String) map.get("title"));
        todo.setStatus((String) map.get("status"));
        todo.setContent((String) map.get("content"));


        todo.setAddedDate(Helper.parse((LocalDateTime) map.get("addedDate")));
        todo.setTodoDate(Helper.parse((LocalDateTime) map.get("todoDate")));*/


        Todo todo=template.queryForObject(query,new TodoRowMapper(),id);
        return todo;
    }

    public List<Todo> getAllTodos() throws ParseException {
        String query = "Select *  from todos";
/*        List<Map<String, Object>> maps = template.queryForList(query);
        List<Todo> allTodo = new ArrayList<>();
        for (Map map : maps) {
            Todo todo = new Todo();
            todo.setId((int) map.get("id"));
            todo.setTitle((String) map.get("title"));
            todo.setStatus((String) map.get("status"));
            todo.setContent((String) map.get("content"));


            todo.setAddedDate(Helper.parse((LocalDateTime) map.get("addedDate")));
            todo.setTodoDate(Helper.parse((LocalDateTime) map.get("todoDate")));
            allTodo.add(todo);
        }
        return allTodo;*/
        return template.query(query, new TodoRowMapper());
    }

    public void updateTodo(int id,Todo newTodo)
    {
        String query="update todos set title=?,status=?,content=?,addedDate=?,todoDate=? where id=?";
        template.update(query,newTodo.getTitle(), newTodo.getStatus(),newTodo.getContent(),newTodo.getAddedDate(),newTodo.getTodoDate(),id);

    }
    public void deleteTodo(int id)
    {
        String query="delete from todos where id=?";
        template.update(query,id);

    }
    public void delelteBatch(int[] ids)
    {
        String query="delete from todos where id=?";
        template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id=ids[i];
                ps.setInt(1,id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });
    }
}

