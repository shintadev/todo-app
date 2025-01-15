package com.shintadev.todo_app_be.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shintadev.todo_app_be.model.Todo;
import com.shintadev.todo_app_be.repository.TodoRepo;
import com.shintadev.todo_app_be.service.TodoService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

  @Autowired
  private TodoRepo todoRepo;

  @Override
  @Transactional
  public ResponseEntity<?> create(Todo todo) {
    try {
      int maxOrderNumber = todoRepo.findMaxOrderNumber().orElse(0);
      todo.setOrderNumber(maxOrderNumber + 1);
      todoRepo.save(todo);
      return ResponseEntity.ok(todo);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> readAll() {
    return ResponseEntity.ok(todoRepo.findAllSorted());
  }

  @Override
  public ResponseEntity<?> read(String id) {
    return ResponseEntity.ok(todoRepo.findById(id));
  }

  @Override
  @Transactional
  public ResponseEntity<?> update(String id, Todo todo) {
    try {
      return todoRepo.findById(id).map(t -> {
        if (todo.getTitle() != null && !todo.getTitle().equals(t.getTitle())) {
          t.setTitle(todo.getTitle());
        }
        t.setCompleted(todo.isCompleted());
        todoRepo.save(t);
        return ResponseEntity.ok(t);
      }).orElse(ResponseEntity.notFound().build());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  @Transactional
  public ResponseEntity<?> delete(String id) {
    try {
      return todoRepo.findById(id).map(t -> {
        todoRepo.delete(t);
        return ResponseEntity.ok().build();
      }).orElse(ResponseEntity.notFound().build());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  @Transactional
  public ResponseEntity<?> deleteMulti(String[] ids) {
    try {
      for (String id : ids) {
        todoRepo.deleteById(id);
      }
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> readPendingTodos() {
    return ResponseEntity.ok(todoRepo.findPendingTodosSorted());
  }

  @Override
  public ResponseEntity<?> readCompletedTodos() {
    return ResponseEntity.ok(todoRepo.findCompletedTodosSorted());
  }
}
