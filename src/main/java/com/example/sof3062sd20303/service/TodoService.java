package com.example.sof3062sd20303.service;

import com.example.sof3062sd20303.entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();

    Todo findById (Long id);

    Todo add(Todo todo);

    Todo update(Todo todo, long id);

    Todo delete(Long id);
}
