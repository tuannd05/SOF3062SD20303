package com.example.sof3062sd20303.controller;

import com.example.sof3062sd20303.entity.Todo;
import com.example.sof3062sd20303.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//  them phan tu @RequiredArgsConstructor -> khai bao constructor de su dung tang service
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")

public class TodoController {
    private final TodoService todoService;
//    api/todos/show
    @GetMapping("/show")
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
}
