package com.erza.todotask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResult {
    private String message;

    private HttpStatus status;

    private String description;

    private Integer priority;



    public TaskResult(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    public TaskResult(String description, Integer priority, HttpStatus status) {
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
