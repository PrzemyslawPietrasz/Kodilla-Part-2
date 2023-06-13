package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {


    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock private AdminConfig adminConfig;

    @Captor
    private ArgumentCaptor<Mail> mailCaptor;

    @Test
    public void fetchTrelloBoards_ReturnsListOfTrelloBoards() {
        // Given
        List<TrelloBoardDto> expectedBoards = Collections.singletonList(new TrelloBoardDto("test-id", "test-board", Collections.emptyList()));
        when(trelloClient.getTrelloBoards()).thenReturn(expectedBoards);

        // When
        List<TrelloBoardDto> fetchedBoards = trelloService.fetchTrelloBoards();

        // Then
        assertEquals(expectedBoards, fetchedBoards);
    }

    @Test
    public void createTrelloCard_SendsEmailAndReturnsCreatedCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test-name", "test-description", "test-pos", "test-listId");
        CreatedTrelloCardDto expectedCard = new CreatedTrelloCardDto("test-id", "test-name", "test-url", null);
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(expectedCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        // When
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertEquals(expectedCard, createdCard);
        verify(emailService, times(1)).send(any(Mail.class));
        verify(emailService).send(mailCaptor.capture());

        Mail capturedMail = mailCaptor.getValue();
        assertEquals("admin@test.com", capturedMail.getMailTo());
        assertEquals("Tasks: New Trello Card", capturedMail.getSubject());
        assertEquals(" New card: test-nameHas been created on your Trello account", capturedMail.getMessage());
    }
}
