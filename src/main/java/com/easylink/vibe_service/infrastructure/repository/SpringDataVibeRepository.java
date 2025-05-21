package com.easylink.vibe_service.infrastructure.repository;

import com.easylink.vibe_service.domain.model.Vibe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataVibeRepository extends JpaRepository<Vibe, UUID> {
}
