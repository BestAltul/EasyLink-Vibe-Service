package com.easylink.vibe_service.web.controller;

import com.easylink.vibe_service.VibeServiceApplication;
import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.Vibe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = VibeServiceApplication.class)
@AutoConfigureMockMvc
public class VibeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VibeRepositoryPort vibeRepository;

    private final UUID accountId = UUID.randomUUID();

    @BeforeEach
    void setup(){

//        vibeRepository.delete();

        Vibe vibe = new Vibe();
        vibe.setTitle("Test Vibe");
        vibe.setVibeAccountId(accountId);
        vibeRepository.save(vibe);
    }

    @Test
    void shouldReturnVibesForCurrentUser() throws Exception{
        mockMvc.perform(get("/api/vibes")
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                                .jwt(jwt -> jwt.subject(accountId.toString()))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Vibe"));
    }
}
