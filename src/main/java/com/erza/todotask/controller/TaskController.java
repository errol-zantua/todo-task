package com.erza.todotask.controller;

import com.erza.todotask.handler.TaskResponseHandler;
import com.erza.todotask.model.Task;
import com.erza.todotask.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            return TaskResponseHandler.generateResponse(task.get(), HttpStatus.OK);
        } else {
            return TaskResponseHandler.generateResponse("task id " + id + " not found", task.get(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveTask(@RequestBody Task task) {
        if (task.getDescription() == null || task.getDescription().isEmpty()) {
            return TaskResponseHandler.generateResponse("description should not be null or empty", task, HttpStatus.BAD_REQUEST);
        }

        if (task.getPriority() == null) {
            return TaskResponseHandler.generateResponse("priority should not be null or empty", task, HttpStatus.BAD_REQUEST);
        }

        Task savedTask = new Task(task.getDescription(), task.getPriority());
        taskRepository.save(savedTask);

        return TaskResponseHandler.generateResponse(savedTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Optional<Task> updatedTask = taskRepository.findById(id);

        if (updatedTask.isPresent()) {
            taskRepository.save(task);
            return TaskResponseHandler.generateResponse(task, HttpStatus.OK);
        } else {
            return TaskResponseHandler.generateResponse("task id " + id + " not found", task, HttpStatus.NOT_FOUND);
        }
    }
}
