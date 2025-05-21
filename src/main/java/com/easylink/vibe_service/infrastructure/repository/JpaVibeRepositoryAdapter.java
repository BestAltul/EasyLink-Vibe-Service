package com.easylink.vibe_service.infrastructure.repository;

import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.Vibe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaVibeRepositoryAdapter  implements VibeRepositoryPort {

    private final SpringDataVibeRepository delegate;

    @Override
    public Vibe save(Vibe vibe){
        return  delegate.save(vibe);
    }
}
