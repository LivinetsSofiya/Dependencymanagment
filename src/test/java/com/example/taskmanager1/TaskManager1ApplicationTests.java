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
//Jenny. I love you
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
    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setTitle("Delete Test Task");
        task.setDescription("Delete Test Description");
        task.setCompleted(false);
        taskRepository.save(task);
        taskRepository.deleteById(task.getId());
        Task deletedTask = taskRepository.findById(task.getId()).orElse(null);
        assertNull(deletedTask);
    }
    @Test
    public void testEditTask() {
        Task task = new Task();
        task.setTitle("Original Task");
        task.setDescription("Original Description");
        task.setCompleted(false);
        taskRepository.save(task);

        Task editedTask = taskRepository.findById(task.getId()).orElse(null);
        assertNotNull(editedTask);

        editedTask.setTitle("Updated Task");
        editedTask.setDescription("Updated Description");
        editedTask.setCompleted(true);
        taskRepository.save(editedTask);

        Task updatedTask = taskRepository.findById(editedTask.getId()).orElse(null);
        assertNotNull(updatedTask);
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals("Updated Description", updatedTask.getDescription());
        assertTrue(updatedTask.isCompleted());
    }
}
