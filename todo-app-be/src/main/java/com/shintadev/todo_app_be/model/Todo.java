package com.shintadev.todo_app_be.model;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "todos")
public class Todo {

  @Id
  @GeneratedValue(generator = "UUID")
  @UuidGenerator
  private String id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
  private boolean completed;

  @Column(name = "order_number", nullable = false, unique = true)
  private int orderNumber;
}
