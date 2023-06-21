package com.crud.tasks.scheduler;

import static org.junit.jupiter.api.Assertions.*;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.EmailTemplateSelector;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailSchedulerTest {
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void sendInformationEmailTest() {
        //Given
        when(adminConfig.getAdminMail()).thenReturn("mail@mail.com");
        when(taskRepository.count()).thenReturn(5L);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        verify(simpleEmailService, times(1)).
                send(argThat(
                                new MailMatcher(
                                        new Mail("mail@mail.com", "", ""))),
                        argThat(new EmailTemplateSelectorMatcher(EmailTemplateSelector.SCHEDULED_EMAIL)));
    }

    private class MailMatcher implements ArgumentMatcher<Mail> {
        private final Mail expected;

        public MailMatcher(Mail expected) {
            this.expected = expected;
        }

        @Override
        public boolean matches(Mail mail) {
            return mail.getMailTo().equals(expected.getMailTo());
        }
    }

    private class EmailTemplateSelectorMatcher implements ArgumentMatcher<EmailTemplateSelector> {

        private final EmailTemplateSelector selector;

        public EmailTemplateSelectorMatcher(EmailTemplateSelector selector) {
            this.selector = selector;
        }

        @Override
        public boolean matches(EmailTemplateSelector selectorMatcher) {
            return selectorMatcher.equals(selector);
        }
    }
}






