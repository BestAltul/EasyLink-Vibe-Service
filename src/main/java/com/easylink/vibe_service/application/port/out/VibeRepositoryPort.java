package com.easylink.vibe_service.application.port.out;

import com.easylink.vibe_service.domain.model.Vibe;

public interface VibeRepositoryPort {
    Vibe save(Vibe vibe);
}
