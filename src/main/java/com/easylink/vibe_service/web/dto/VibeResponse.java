package com.easylink.vibe_service.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VibeResponse {
    private UUID id;
    private String title;
}
