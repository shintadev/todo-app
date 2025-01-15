package com.shintadev.todo_app_be.service;

import org.springframework.http.ResponseEntity;

import com.shintadev.todo_app_be.model.Todo;

public interface TodoService {

  ResponseEntity<?> create(Todo todo);

  ResponseEntity<?> readAll();

  ResponseEntity<?> read(String id);

  ResponseEntity<?> readPendingTodos();

  ResponseEntity<?> readCompletedTodos();

  ResponseEntity<?> update(String id, Todo todo);

  ResponseEntity<?> delete(String id);

  ResponseEntity<?> deleteMulti(String[] ids);
}
