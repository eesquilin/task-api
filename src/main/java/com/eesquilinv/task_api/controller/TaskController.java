package com.eesquilinv.task_api.controller;

import com.eesquilinv.task_api.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eesquilinv.task_api.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<Task>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTask();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long Id){
        Optional<Task> taskOptional = taskService.getTaskById(Id);
        return taskOptional.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long Id, @RequestBody Task task){
        Task updatedTask = taskService.updateTask(Id, task);
        if (updatedTask != null){
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("id") Long id){
        Optional<Task> existingTask = taskService.getTaskById(id);
        if(existingTask.isPresent()){
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
