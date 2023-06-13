package com.crud.tasks.service;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DbServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private DbService dbService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAllTasks() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", "Description 1"));
        tasks.add(new Task(2L, "Task 2", "Description 2"));

        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<Task> result = dbService.getAllTasks();

        // Assert
        assertEquals(tasks, result);
    }

    @Test
    public void shouldReturnTaskByIdWhenTaskExists() throws TaskNotFoundException {
        // Arrange
        Long taskId = 1L;
        Task task = new Task(taskId, "Task", "Description");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        Task result = dbService.getTask(taskId);

        // Assert
        assertEquals(task, result);
    }

    @Test
    public void shouldThrowExceptionWhenTaskByIdDoesNotExist() {
        // Arrange
        Long taskId = 1L;

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(taskId));
    }

    @Test
    public void shouldSaveTask() {
        // Arrange
        Task task = new Task(1L, "Task", "Description");

        when(taskRepository.save(task)).thenReturn(task);

        // Act
        Task result = dbService.saveTask(task);

        // Assert
        assertEquals(task, result);
    }

    @Test
    public void shouldDeleteTaskById() {
        // Arrange
        Long taskId = 1L;

        // Act
        dbService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
