package com.example.taskmanager1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TaskManager1ApplicationTests {
    @Autowired
    private TaskRepository taskRepository;
    @Test
    public void testAddTask() {

        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);


        taskRepository.save(task);


        Task savedTask = taskRepository.findById(task.getId()).orElse(null);
        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
        assertEquals("Test Description", savedTask.getDescription());
        assertFalse(savedTask.isCompleted());
    }
}
