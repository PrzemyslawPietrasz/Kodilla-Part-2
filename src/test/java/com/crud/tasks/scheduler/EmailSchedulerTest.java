package com.crud.tasks.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

public class EmailSchedulerTest {

    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        emailScheduler = new EmailScheduler(simpleEmailService, taskRepository, adminConfig);
    }

    @Test
    public void testSendInformationEmail() {
        // Given
        long taskCount = 1;
        when(taskRepository.count()).thenReturn(taskCount);
        when(adminConfig.getAdminMail()).thenReturn("admin@example.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(taskRepository, times(1)).count();
        verify(adminConfig, times(1)).getAdminMail();
        verify(simpleEmailService, times(1)).send(any(Mail.class));
    }
}
