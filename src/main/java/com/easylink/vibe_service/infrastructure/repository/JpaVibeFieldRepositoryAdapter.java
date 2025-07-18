package com.easylink.vibe_service.infrastructure.repository;


import com.easylink.vibe_service.application.port.out.VibeFieldRepositoryPort;
import com.easylink.vibe_service.domain.model.VibeField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaVibeFieldRepositoryAdapter implements VibeFieldRepositoryPort {

    private SpringDataVibeFieldRepository springDataVibeFieldRepository;

    @Override
    public List<VibeField> findAllById(List<UUID> ids) {

        List<VibeField> vibeFields = springDataVibeFieldRepository.findAllById(ids);

        return vibeFields;
    }
}
