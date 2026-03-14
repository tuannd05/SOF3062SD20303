package com.example.sof3062sd20303.controller;

import com.example.sof3062sd20303.entity.Todo;
import com.example.sof3062sd20303.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  them phan tu @RequiredArgsConstructor -> khai bao constructor de su dung tang service
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")

public class TodoController {
    private final TodoService todoService;
//    api/todos/show
    @GetMapping
    public List<Todo> getAllTodos() {

        return todoService.findAll();
    }
//    api/todos/id
    @GetMapping("{id}")
    public Todo getTodo(
            @PathVariable("id") Long id
    ){
        return todoService.findById(id);
    }
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.add(todo);
    }

    @PutMapping("{id}")
    public Todo updateTodo(
            @PathVariable("id") Long id,
            @RequestBody Todo todo
    ) {
        todo.setId(id);
        return todoService.add(todo);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable("id") Long id) {
        todoService.delete(id);
    }
}
