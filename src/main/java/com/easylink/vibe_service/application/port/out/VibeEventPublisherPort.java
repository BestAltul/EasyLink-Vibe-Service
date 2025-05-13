package com.easylink.vibe_service.application.port.out;

import com.easylink.vibe_service.application.event.VibeCreatedEvent;

public interface VibeEventPublisherPort {
    void publishVibeCreatedEvent(VibeCreatedEvent event);
}
