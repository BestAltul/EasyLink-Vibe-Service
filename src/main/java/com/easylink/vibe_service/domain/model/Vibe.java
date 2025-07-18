package com.easylink.vibe_service.domain.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Vibe {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable=false)
    private UUID vibeAccountId;

    private String title;

    @OneToMany(mappedBy = "vibe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VibeField> fields = new ArrayList<>();
}
