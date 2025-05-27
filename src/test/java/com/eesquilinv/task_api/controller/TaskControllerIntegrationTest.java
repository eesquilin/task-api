package com.eesquilinv.task_api.controller;

import com.eesquilinv.task_api.model.Task;
import com.eesquilinv.task_api.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {


    @Autowired
    TestRestTemplate testRestTemplate;


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
    @Test
    public void shouldReturnTaskById(){
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDueDate("2025-05-20");
        newTask.setDescription("This test retrieves task by id.");
        newTask.setTaskStatus(TaskStatus.INPROGRESS);

        //Post the test-newTask object into the test-entity.
        ResponseEntity<Task> response = testRestTemplate.postForEntity("/tasks", newTask, Task.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Task createdTask = response.getBody();
        assertNotNull(createdTask);

        ResponseEntity<Task> retrievedResponse = testRestTemplate.getForEntity("/tasks/" + createdTask.getId(), Task.class);
        assertEquals(HttpStatus.OK, retrievedResponse.getStatusCode());
        assertNotNull(retrievedResponse.getBody());
        assertEquals(createdTask.getId(), retrievedResponse.getBody().getId());
        assertEquals("Test Task", retrievedResponse.getBody().getTitle());
        assertEquals("2025-05-20", retrievedResponse.getBody().getDueDate());
        assertEquals("This test retrieves task by id.", retrievedResponse.getBody().getDescription());
        assertEquals(TaskStatus.INPROGRESS, retrievedResponse.getBody().getTaskStatus());

    }

    @Test
    public void shouldUpdateTaskTest(){
        Task newTask = new Task();
        newTask.setTitle("Test Task");
        newTask.setDueDate("05-24-2024");
        newTask.setDescription("Testing");
        newTask.setTaskStatus(TaskStatus.INPROGRESS);

        ResponseEntity<Task> response = testRestTemplate.postForEntity("/tasks/", newTask, Task.class);

        Task createdTask = new Task();
        createdTask.setId(newTask.getId());
        createdTask.setTitle("Updated Task");

        //ResponseEntity<Task> updatedResponse = testRestTemplate.put("/tasks/",createdTask);
    }

    @Test
    public void shouldDeleteTaskTest(){}
}
