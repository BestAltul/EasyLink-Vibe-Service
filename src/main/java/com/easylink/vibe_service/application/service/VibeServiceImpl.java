package com.easylink.vibe_service.application.service;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;

public class VibeServiceImpl implements CreateVibeUseCase {

    @Override
    public VibeDto create(CreateVibeCommand command) {
        return null;
    }
}
