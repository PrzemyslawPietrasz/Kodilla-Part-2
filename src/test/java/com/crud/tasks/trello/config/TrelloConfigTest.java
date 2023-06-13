package com.crud.tasks.trello.config;

import static org.junit.jupiter.api.Assertions.*;

import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "trello.api.endpoint.prod=https://api.trello.com",
        "trello.app.key=testAppKey",
        "trello.app.token=testAppToken",
        "trello.app.username=testUsername",
        "trello.user=testUser"
})
public class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Value("${trello.api.endpoint.prod}")
    private String expectedTrelloApiEndpoint;

    @Value("${trello.app.key}")
    private String expectedTrelloAppKey;

    @Value("${trello.app.token}")
    private String expectedTrelloToken;

    @Value("${trello.app.username}")
    private String expectedTrelloUsername;

    @Value("${trello.user}")
    private String expectedTrelloUser;

    @Test
    public void testTrelloApiEndpoint() {
        // Given

        // When
        String actualTrelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();

        // Then
        assertEquals(expectedTrelloApiEndpoint, actualTrelloApiEndpoint);
    }

    @Test
    public void testTrelloAppKey() {
        // Given

        // When
        String actualTrelloAppKey = trelloConfig.getTrelloAppKey();

        // Then
        assertEquals(expectedTrelloAppKey, actualTrelloAppKey);
    }

    @Test
    public void testTrelloToken() {
        // Given

        // When
        String actualTrelloToken = trelloConfig.getTrelloToken();

        // Then
        assertEquals(expectedTrelloToken, actualTrelloToken);
    }

    @Test
    public void testTrelloUsername() {
        // Given

        // When
        String actualTrelloUsername = trelloConfig.getTrelloUsername();

        // Then
        assertEquals(expectedTrelloUsername, actualTrelloUsername);
    }

}
