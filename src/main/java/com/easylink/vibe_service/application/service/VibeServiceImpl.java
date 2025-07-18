package com.easylink.vibe_service.application.service;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.UpdateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.mapper.VibeDtoMapper;
import com.easylink.vibe_service.application.port.in.*;
import com.easylink.vibe_service.application.port.out.VibeFieldRepositoryPort;
import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.Vibe;
import com.easylink.vibe_service.domain.model.VibeField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VibeServiceImpl implements CreateVibeUseCase, UpdateVibeUseCase, DeleteVibeUseCase, GetVibeUseCase, GetVibeByIdUseCase {

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

        VibeDto vibeDto = VibeDtoMapper.toDto(vibe);

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

    @Override
    public void delete(UUID id, UUID accountId) {
        Vibe vibe = vibeRepositoryPort.findById(id).orElseThrow(()->new RuntimeException("Vibe not found"));

        if(!vibe.getVibeAccountId().equals(accountId)){
            throw new SecurityException("Access denied");
        }

        vibeRepositoryPort.delete(vibe);
    }

    @Override
    public List<VibeDto> findAllByVibeAccountId(UUID accountId) {

        List<Vibe> vibeDtoList = vibeRepositoryPort.findAllByAccountId(accountId);

        List<VibeDto> dtoList = vibeDtoList.stream().map(VibeDtoMapper::toDto).toList();

        return dtoList;
    }

    @Override
    public VibeDto getVibeById(UUID id) {
        Vibe vibe = vibeRepositoryPort.findById(id).orElseThrow(()->new RuntimeException("Vibe not found"));

        VibeDto vibeDto = VibeDtoMapper.toDto(vibe);

        return vibeDto;
    }
}
