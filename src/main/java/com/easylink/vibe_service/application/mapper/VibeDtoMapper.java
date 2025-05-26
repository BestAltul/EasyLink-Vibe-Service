package com.easylink.vibe_service.application.mapper;

import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.domain.model.Vibe;

public class VibeDtoMapper {
    public static VibeDto toDto(Vibe vibe){
        VibeDto vibeDto = new VibeDto();
        vibeDto.setTitle(vibe.getTitle());
        vibeDto.setId(vibe.getId());
        return vibeDto;
    }
}
