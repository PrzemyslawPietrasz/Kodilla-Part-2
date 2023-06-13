package com.crud.tasks.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalHttpErrorHandlerTest {


    private final GlobalHttpErrorHandler globalHttpErrorHandler = new GlobalHttpErrorHandler();

    @Test
    public void handleTaskNotFoundException_ReturnsBadRequest() {
        // Given
        TaskNotFoundException exception = new TaskNotFoundException();

        // When
        ResponseEntity<Object> response = globalHttpErrorHandler.handleTaskNotFoundException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Task with give id dosen't exist", response.getBody());
    }
}