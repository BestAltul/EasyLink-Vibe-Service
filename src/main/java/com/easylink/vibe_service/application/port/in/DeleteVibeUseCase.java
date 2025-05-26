package com.easylink.vibe_service.application.port.in;

import java.util.UUID;

public interface DeleteVibeUseCase {
    void delete(UUID id, UUID accountId);
}
