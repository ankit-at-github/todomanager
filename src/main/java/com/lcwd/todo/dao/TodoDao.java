package com.lcwd.todo.dao;

import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@Repository
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);


    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        //create table if doesn't exit

        String createTable = "create table IF NOT EXISTS todos(id int primary key, title varchar(100) not null, content varchar(100), status varchar(10) not null, addedDate datetime, todoDate datetime);";

        int update = template.update(createTable);

        logger.info("Todo table created {}", update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database

    public Todo saveTodo(Todo todo) {
        String insertQuery = "insert into todos(id, title, content, status, addedDate, todoDate) values(?, ?, ?, ?, ?, ?)";

        int rows = template.update(insertQuery, todo.getId(), todo.getTitle()
                , todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getToDoDate());

        logger.info("JDBC Operation: {} inserted", rows);

        return todo;
    }

    //get single todo from database
    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos where id = ?";
        Todo todo = template.queryForObject(query, new TodoRowMapper(), id);


//        Map<String, Object> todoData = template.queryForMap(query, id);
//        Todo todo = new Todo();
//        todo.setId((int) todoData.get("id"));
//        todo.setTitle((String) todoData.get("title"));
//        todo.setContent((String) todoData.get("content"));
//        todo.setStatus((String) todoData.get("status"));
// Creating formatter for Date
//        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
//        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));

        return todo;
    }

    //get all todo from database
    public List<Todo> getAllTodos() {
        String query = "select * from todos";

        List<Todo> todos = template.query(query, new TodoRowMapper());

//        List<Map<String, Object>> maps = template.queryForList(query);
//
//        //Converting map to todo object
//
//        List<Todo> todos = maps.stream().map((map) -> {
//            Todo todo = new Todo();
//
//            todo.setId((int)map.get("id"));
//            todo.setTitle((String)map.get("title"));
//            todo.setContent((String)map.get("content"));
//            todo.setStatus((String)map.get("status"));
//            try {
//                todo.setAddedDate(Helper.parseDate((LocalDateTime)map.get("addedDate")));
//                todo.setToDoDate(Helper.parseDate((LocalDateTime)map.get("todoDate")));
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            return todo;
//
//        }).collect(Collectors.toList());

        return todos;
    }

    //update todo
    public Todo updateTodo(int id, Todo newTodo) {
        String query = "Update todos set title=?, content=?, status=?, addedDate=?, todoDate=? where id=?";

        int update = template.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getToDoDate(), id);
        logger.info("UPDATED {}", update);
        newTodo.setId(id);

        return newTodo;
    }

    //delete todo from database
    public void delete(int id) {
        String query = "delete from todos where id=?";
        int row = template.update(query, id);
        logger.info("DELETED : {}", row);
    }

    //delete multiple
    public void deleteMultiple(int[] ids) {
        String query = "delete from todos where id=?";

        //Using foreach loop
//        for(int id : ids)
//            template.update(query, id);

        //Batch Update
        int[] ints = template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for (int i : ints)
            logger.info("DELETED : {}", i);
    }

}
