package com.easylink.vibe_service.application.service;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;
import com.easylink.vibe_service.application.port.out.VibeFieldRepositoryPort;
import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.Vibe;
import com.easylink.vibe_service.domain.model.VibeField;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class VibeServiceImpl implements CreateVibeUseCase {

    private final VibeRepositoryPort vibeRepositoryPort;
    private final VibeFieldRepositoryPort vibeFieldRepositoryPort;

    @Override
    public VibeDto create(CreateVibeCommand command) {

        List<VibeField> vibeFieldList = vibeFieldRepositoryPort.findAllById(command.getFieldIds());

        Vibe vibe = new Vibe();
        vibe.setTitle(command.getTitle());
        vibe.setVibeAccountId(command.getAccountId());
        vibe.setFields(vibeFieldList);

        Vibe savedVibe = vibeRepositoryPort.save(vibe);
        VibeDto vibeDto = new VibeDto();
        vibeDto.setId(vibe.getId());
        vibeDto.setTitle(vibe.getTitle());

        return vibeDto;
    }
}
