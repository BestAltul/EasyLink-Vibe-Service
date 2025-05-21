package com.easylink.vibe_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateVibeCommand {
    private String title;
    private List<Long> fieldIds;
}
