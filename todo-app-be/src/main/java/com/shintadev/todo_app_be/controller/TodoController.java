package com.shintadev.todo_app_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shintadev.todo_app_be.model.Todo;
import com.shintadev.todo_app_be.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  @Autowired
  private TodoService todoService;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Todo todo) {
    return ResponseEntity.ok(todoService.create(todo));
  }

  @GetMapping
  public ResponseEntity<?> readAll() {
    return ResponseEntity.ok(todoService.readAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> read(@PathVariable String id) {
    return ResponseEntity.ok(todoService.read(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable String id, @RequestBody Todo todo) {
    return todoService.update(id, todo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable String id) {
    return ResponseEntity.ok(todoService.delete(id));
  }

  @DeleteMapping("/delete-multi")
  public ResponseEntity<?> deleteMulti(@RequestBody String[] ids) {
    return ResponseEntity.ok(todoService.deleteMulti(ids));
  }

  @GetMapping("/pending")
  public ResponseEntity<?> readPendingTodos() {
    return ResponseEntity.ok(todoService.readPendingTodos());
  }

  @GetMapping("/completed")
  public ResponseEntity<?> readCompletedTodos() {
    return ResponseEntity.ok(todoService.readCompletedTodos());
  }
}
