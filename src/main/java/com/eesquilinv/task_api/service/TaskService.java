package com.eesquilinv.task_api.service;

import com.eesquilinv.task_api.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eesquilinv.task_api.repository.TaskRepository;


import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskRepository taskRepository;

    //method to create task
    public Task createTask(Task task){
       return taskRepository.save(task);

    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long Id){
        return taskRepository.findById(Id);

    }

    public Task updateTask(Long Id, Task task){
        Optional<Task> existingTaskOptional = taskRepository.findById(Id);
        if(existingTaskOptional.isPresent()){
            Task existingTask = existingTaskOptional.get();
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setTaskStatus(task.getTaskStatus());
            return taskRepository.save(existingTask);

        }
        else return null;

    }

    public void deleteTask(Long Id){
        taskRepository.deleteById(Id);
        log.atInfo().log("Task with ID{} has been deleted.", Id);

    }


}
