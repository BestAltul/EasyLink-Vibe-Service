package com.easylink.vibe_service.application.port.out;


import com.easylink.vibe_service.domain.model.VibeField;

import java.util.List;
import java.util.UUID;

public interface VibeFieldRepositoryPort {
    List<VibeField> findAllById(List<UUID> ids);
}
