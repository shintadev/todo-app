package com.shintadev.todo_app_be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shintadev.todo_app_be.model.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, String>, JpaSpecificationExecutor<Todo> {

  Todo findByOrderNumber(int orderNumber);

  @Query("SELECT t FROM Todo t ORDER BY t.orderNumber ASC")
  List<Todo> findAllSorted();

  @Query("SELECT t FROM Todo t WHERE t.completed = false ORDER BY t.orderNumber ASC")
  List<Todo> findPendingTodosSorted();

  @Query("SELECT t FROM Todo t WHERE t.completed = true ORDER BY t.orderNumber ASC")
  List<Todo> findCompletedTodosSorted();

  @Query("SELECT MAX(t.orderNumber) FROM Todo t")
  Optional<Integer> findMaxOrderNumber();
}
