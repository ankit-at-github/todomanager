package com.lcwd.todo;

import com.lcwd.todo.dao.TodoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);
    @Autowired
    private TodoDao todoDao;

    public static void main(String[] args) {
        SpringApplication.run(TodoManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		System.out.println("Application Started");
//		JdbcTemplate template = todoDao.getTemplate();
//		logger.info("Template Object : {}", template);
//		logger.info("Template Object : {}", template.getDataSource().getConnection());

//		Todo todo = new Todo();
//		todo.setId(12);
//		todo.setTitle("Java Placement Course");
//		todo.setContent("I've to learn Java Course");
//		todo.setStatus("PENDING");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//		todoDao.saveTodo(todo);

//		List<Todo> allTodos = todoDao.getAllTodos();
//		logger.info("All Todos : {}", allTodos);

//		Todo todo = todoDao.getTodo(12);
//		logger.info("TODO : {}", todo);

//		todo.setTitle("Learn Spring Boot Course");
//		todo.setContent("I have to learn Spring Boot");
//		todo.setStatus("Done");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//		todoDao.updateTodo(1230, todo);

//		todoDao.delete(1230);

//		todoDao.deleteMultiple(new int[]{32, 123});

    }
}
