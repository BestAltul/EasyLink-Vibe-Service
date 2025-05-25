package com.easylink.vibe_service.application.port.in;

import com.easylink.vibe_service.application.dto.UpdateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.web.dto.UpdateVibeRequest;

public interface UpdateVibeUseCase {
    VibeDto update(UpdateVibeCommand updateVibeCommand);
}
