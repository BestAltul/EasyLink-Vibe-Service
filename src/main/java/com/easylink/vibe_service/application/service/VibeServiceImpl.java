package com.easylink.vibe_service.application.service;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;
import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.Vibe;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VibeServiceImpl implements CreateVibeUseCase {

    private final VibeRepositoryPort vibeRepositoryPort;

    @Override
    public VibeDto create(CreateVibeCommand command) {
        Vibe vibe = new Vibe();
        vibe.setTitle(command.getTitle());
        vibe.setVibeAccountId(command.getAccountId());

        Vibe savedVibe = vibeRepositoryPort.save(vibe);

        return new VibeDto();
    }



}
