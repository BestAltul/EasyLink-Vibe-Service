package com.easylink.vibe_service.web.mapper;

import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.web.dto.VibeResponse;

public class VibeResponseMapper {
    public static VibeResponse toResponse(VibeDto vibeDto){
        VibeResponse response = new VibeResponse();
        response.setId(vibeDto.getId());
        response.setTitle(vibeDto.getTitle());
        return response;
    }
}
