package com.easylink.vibe_service.web.controller;

import com.easylink.vibe_service.application.dto.UpdateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;
import com.easylink.vibe_service.application.port.in.UpdateVibeUseCase;
import com.easylink.vibe_service.web.dto.CreateVibeRequest;
import com.easylink.vibe_service.web.dto.UpdateVibeRequest;
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
    private final UpdateVibeUseCase updateVibeUseCase;

    public ResponseEntity<VibeResponse> create (@RequestBody CreateVibeRequest request, @AuthenticationPrincipal Jwt jwt){

        UUID accountId = UUID.fromString(jwt.getSubject());

        VibeDto vibeDto = createVibeUseCase.create(VibeRequestMapper.toCommand(request,accountId));

        return ResponseEntity.ok(VibeResponseMapper.toResponse(vibeDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VibeResponse> update(
            @PathVariable UUID id,
            @RequestBody UpdateVibeRequest request,
            @AuthenticationPrincipal Jwt jwt){
        UUID accountId = UUID.fromString(jwt.getSubject());

        UpdateVibeCommand updateVibeCommand = new UpdateVibeCommand();
        updateVibeCommand.setId(id);
        updateVibeCommand.setTitle(request.getTitle());
        updateVibeCommand.setAccountId(accountId);
        updateVibeCommand.setFieldIds(request.getFieldIds());

        VibeDto vibeDto = updateVibeUseCase.update(updateVibeCommand);
        return ResponseEntity.ok(VibeResponseMapper.toResponse(vibeDto));
    }
}

