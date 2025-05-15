package com.eesquilinv.task_api.controller;

import com.eesquilinv.task_api.model.Task;
import com.eesquilinv.task_api.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    private String getUrl(){
        return "localhost:" + port;
    }

    @Test
    public void shouldCreateNewTask(){
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDueDate("2025-05-20");
        newTask.setDescription("This is a test task.");
        newTask.setTaskStatus(TaskStatus.TODO);

        ResponseEntity<Task> response = testRestTemplate.postForEntity("/tasks", newTask, Task.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Task", response.getBody().getTitle());
        assertEquals("2025-05-20", response.getBody().getDueDate());
        assertEquals("This is a test task.", response.getBody().getDescription());
        assertEquals(TaskStatus.TODO, response.getBody().getTaskStatus());

    }

    public void shouldReturnTaskById(Long Id){
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDueDate("2025-05-20");
        newTask.setDescription("This test retrieves task by id.");
        newTask.setTaskStatus(TaskStatus.INPROGRESS);

        //Post the test-newTask object into the test-entity.
        ResponseEntity<Task> response = testRestTemplate.postForEntity("/tasks", newTask, Task.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());




    }
}
