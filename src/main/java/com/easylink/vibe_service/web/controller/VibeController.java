package com.easylink.vibe_service.web.controller;

import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;
import com.easylink.vibe_service.web.dto.CreateVibeRequest;
import com.easylink.vibe_service.web.dto.VibeResponse;
import com.easylink.vibe_service.web.mapper.VibeRequestMapper;
import com.easylink.vibe_service.web.mapper.VibeResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/vibes")
@RequiredArgsConstructor
public class VibeController {

    private final CreateVibeUseCase createVibeUseCase;

    public ResponseEntity<VibeResponse> create (@RequestBody CreateVibeRequest request, @AuthenticationPrincipal Jwt jwt){

        UUID accountId = UUID.fromString(jwt.getSubject());

        VibeDto vibeDto = createVibeUseCase.create(VibeRequestMapper.toCommand(request,accountId));

        return ResponseEntity.ok(VibeResponseMapper.roResponse(vibeDto));
    }

    public ResponseEntity<VibeResponse> update(@PathVariable UUID id
                                               ){

    }
}

