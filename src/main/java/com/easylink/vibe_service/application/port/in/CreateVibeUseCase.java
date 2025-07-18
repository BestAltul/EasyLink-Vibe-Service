package com.easylink.vibe_service.application.port.in;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;

public interface CreateVibeUseCase {
    VibeDto create(CreateVibeCommand command);
}
