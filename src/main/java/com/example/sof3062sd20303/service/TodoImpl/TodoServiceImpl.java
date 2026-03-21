package com.example.sof3062sd20303.service.TodoImpl;

import com.example.sof3062sd20303.entity.Todo;
import com.example.sof3062sd20303.exception.CustomResourceNotFoundException;
import com.example.sof3062sd20303.repository.TodoRepository;
import com.example.sof3062sd20303.service.TodoService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
//được tiêm qua constructor
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
//    private: chỉ cho phép dùng biến đó bên trong class.
//    final: biến đó chỉ được gán một lần, không thể đổi sang cái khác.
   private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
       return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        return todoRepository
                .findById(id)
                .orElseThrow(() -> new CustomResourceNotFoundException("Todo not found for this id: " + id));
    }

    @Override
    public Todo add(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Todo todo, long id) {
        return todoRepository
                .findById(id)
                .map(existing -> {
                    if (todo.getTitle() != null) existing.setTitle(todo.getTitle());
                    if (todo.getDescription() != null) existing.setDescription(todo.getDescription());
//                    Luôn gán existing.completed = false -> value default
                    existing.setCompleted(todo.isCompleted());
                    return todoRepository.save(existing);
                })
                .orElse(null);
    }

    @Override
    public Todo delete(Long id) {
        Todo deletedTodo = findById(id);
        if (deletedTodo != null) {
            todoRepository.delete(deletedTodo);
        }
        return deletedTodo;
    }
}
