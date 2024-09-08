package com.erza.todotask.handler;

import com.erza.todotask.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskResponseHandler {

    public static ResponseEntity<Object> generateResponse(Task task, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("description", task.getDescription());
        map.put("priority", task.getPriority());
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> generateResponse(List<Task> tasks, HttpStatus status) {
        List<Map<String, Object>> arr = new ArrayList<>();

        for (Task task : tasks) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", task.getId());
            map.put("description", task.getDescription());
            map.put("priority", task.getPriority());

            arr.add(map);
        }

        return new ResponseEntity<>(arr, status);
    }
}
