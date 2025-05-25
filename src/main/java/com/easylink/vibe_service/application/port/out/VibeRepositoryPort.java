package com.easylink.vibe_service.application.port.out;

import com.easylink.vibe_service.domain.model.Vibe;

import java.util.Optional;
import java.util.UUID;

public interface VibeRepositoryPort {
    Vibe save(Vibe vibe);
    Optional<Vibe> findById(UUID id);
}
