package com.crud.tasks.config;

import com.crud.tasks.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "admin.mail=test@example.com"
})
public class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void testAdminMail() {
        // Given
        String expectedAdminMail = "test@example.com";

        // When
        String actualAdminMail = adminConfig.getAdminMail();

        // Then
        assertEquals(expectedAdminMail, actualAdminMail);
    }
}
