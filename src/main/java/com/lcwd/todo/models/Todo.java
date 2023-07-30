package com.lcwd.todo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "JPA_TODOS")
public class Todo {
    @Id
    private int id;
    @Column(name = "todo_title", length = 100)
    private String title;
    @Column(name = "todo_content", length = 1000)
    private String content;
    @Column(name = "todo_status", length = 10)
    private String status;

    //add automatically
    @Column(name = "todo_added_date")
    private Date addedDate;

    //coming from user
    @Column(name = "todo_todo_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date toDoDate;

    public Todo(int id, String title, String content, String status, Date addedDate, Date toDoDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.addedDate = addedDate;
        this.toDoDate = toDoDate;
    }

    public Todo() {

    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getToDoDate() {
        return toDoDate;
    }

    public void setToDoDate(Date toDoDate) {
        this.toDoDate = toDoDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", addedDate=" + addedDate +
                ", toDoDate=" + toDoDate +
                '}';
    }
}