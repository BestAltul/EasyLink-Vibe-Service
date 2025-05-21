package com.easylink.vibe_service.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateVibeRequest {
    private String title;
    private List<Long> fieldIds;
}
