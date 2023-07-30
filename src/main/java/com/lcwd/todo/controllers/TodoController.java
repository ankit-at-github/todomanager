package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);
    Random rand = new Random();
    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo) {

//        String str = null;
//        logger.info("{}", str.length());

        int id = rand.nextInt(999999);
        todo.setId(id);

        //Create date with system default current date

        Date currentDate = new Date();
        logger.info("Current Date : {}", currentDate);
        logger.info("Todo Date {}", todo.getToDoDate());

        todo.setAddedDate(currentDate);

        //create todo
        logger.info("Create Todo");

        //create service to create todo

        Todo todo1 = todoService.createTodo(todo);

        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler() {
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    //get single todo

    //keep same name so that you don't have to write in @PathVariable
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSpecificTodoHandler(@PathVariable int todoId) throws ParseException {
        //another way to create response entity
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    //update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updatedTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId) {
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Successfully Deleted");
    }

    //exception handler
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Null Pointer Exception Generated");
//        return new ResponseEntity<>("Null Pointer Exception Generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
