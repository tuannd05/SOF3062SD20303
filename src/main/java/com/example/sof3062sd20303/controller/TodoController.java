package com.example.sof3062sd20303.controller;

import com.example.sof3062sd20303.entity.Todo;
import com.example.sof3062sd20303.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//  them phan tu @RequiredArgsConstructor -> khai bao constructor de su dung tang service
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")

public class TodoController {
    private final TodoService todoService;
//    @GetMapping: đọc dữ liệu (GET).
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {

        List<Todo> todos = todoService.findAll();

        //cac cach test trang thai
//        return new ResponseEntity<>(todos, HttpStatus.OK);
//        return ResponseEntity.ok(todos);
//        return ResponseEntity.status(HttpStatus.OK).body(todos);

        return ResponseEntity
                .ok()
                .header("Custom-Header","Custom-Value")
                .body(todos);
    }

//    @GetMapping: đọc dữ liệu (GET).
    @GetMapping("{id}")
    public ResponseEntity<Todo> getTodo(
            // lấy giá trị id của url
            @PathVariable("id") Long id
    ){
        Todo todo = todoService.findById(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

//    @PostMapping: tạo mới (POST).
    @PostMapping
    // lấy dữ liệu json để bind
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo savedTodo = todoService.add(todo);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

//    @PutMapping: cập nhật toàn bộ theo id (PUT).
    @PutMapping("{id}")
    public ResponseEntity<Todo> updateTodo(
            @RequestBody Todo todo,
            @PathVariable("id") Long id
    ) {
      Todo updatedTodo = todoService.update(todo, id);
      return new ResponseEntity<>(updatedTodo,HttpStatus.OK);
    }

//    @DeleteMapping: xóa theo id (DELETE).
    @DeleteMapping("{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") Long id) {
       Todo deletedTodo = todoService.delete(id);
       return new ResponseEntity<>(deletedTodo, HttpStatus.OK);
    }
}
