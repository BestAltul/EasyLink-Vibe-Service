package com.easylink.vibe_service.application.service;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.UpdateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.in.CreateVibeUseCase;
import com.easylink.vibe_service.application.port.in.UpdateVibeUseCase;
import com.easylink.vibe_service.application.port.out.VibeFieldRepositoryPort;
import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.Vibe;
import com.easylink.vibe_service.domain.model.VibeField;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class VibeServiceImpl implements CreateVibeUseCase, UpdateVibeUseCase {

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

    @Override
    public VibeDto update(UpdateVibeCommand updateVibeCommand) {

        Vibe vibe = vibeRepositoryPort.findById(updateVibeCommand.getId()).orElseThrow(()->new RuntimeException("Vibe not found"));

        if (!vibe.getVibeAccountId().equals(updateVibeCommand.getAccountId())){
            throw new SecurityException("Access denied!");
        }

        List<VibeField> fieldList = vibeFieldRepositoryPort.findAllById(updateVibeCommand.getFieldIds());
        vibe.setTitle(updateVibeCommand.getTitle());
        vibe.setFields(fieldList);

        Vibe updated = vibeRepositoryPort.save(vibe);

        VibeDto vibeDto = new VibeDto();
        vibeDto.setId(updated.getId());
        vibeDto.setTitle(updateVibeCommand.getTitle());

        return vibeDto;
    }
}
