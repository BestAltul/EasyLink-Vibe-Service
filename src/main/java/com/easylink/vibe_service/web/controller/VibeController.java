package com.easylink.vibe_service.web.controller;

import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;
import com.easylink.vibe_service.web.dto.CreateVibeRequest;
import com.easylink.vibe_service.web.dto.VibeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vibes")
@RequiredArgsConstructor
public class VibeController {

    private final CreateVibeUseCase createVibeUseCase;

    public ResponseEntity<VibeResponse> create (@RequestBody CreateVibeRequest request, @AuthenticationPrincipal Jwt jwt){

        Long accountId = Long.parseLong(jwt.getSubject());

        VibeResponse vibeResponse = createVibeUseCase.create(//здесь как мне конвертировать request в createVibeCommand);

    }

}
