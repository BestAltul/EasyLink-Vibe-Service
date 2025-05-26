package com.easylink.vibe_service.application.service;

import com.easylink.vibe_service.application.dto.CreateVibeCommand;
import com.easylink.vibe_service.application.dto.UpdateVibeCommand;
import com.easylink.vibe_service.application.dto.VibeDto;
import com.easylink.vibe_service.application.port.out.VibeFieldRepositoryPort;
import com.easylink.vibe_service.application.port.out.VibeRepositoryPort;
import com.easylink.vibe_service.domain.model.EmailField;
import com.easylink.vibe_service.domain.model.Vibe;
import com.easylink.vibe_service.domain.model.VibeField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class VibeServiceImplTest {

    @Mock
    VibeRepositoryPort vibeRepositoryPort;

    @Mock
    VibeFieldRepositoryPort vibeFieldRepositoryPort;

    @InjectMocks
    VibeServiceImpl vibeService;

    @Test
    void ShouldCreateVibeSuccessfully(){

        UUID accountId = UUID.randomUUID();
        List<UUID> fieldIds = List.of(UUID.randomUUID(),UUID.randomUUID());

        CreateVibeCommand createVibeCommand = new CreateVibeCommand("Test Vibe",fieldIds,accountId);

        EmailField emailField = new EmailField();
        emailField.setEmail("test@gmail.com");

        List<VibeField> fields = List.of(emailField);

        when(vibeFieldRepositoryPort.findAllById(fieldIds)).thenReturn(fields);

        when(vibeRepositoryPort.save(any())).thenAnswer(invocation->{

            Vibe saved = invocation.getArgument(0);

            saved.setId(UUID.randomUUID());

            return  saved;
        });

        VibeDto vibeDto = vibeService.create(createVibeCommand);

        assertNotNull(vibeDto);
        assertNotNull(vibeDto.getId());
        assertEquals("Test Vibe", vibeDto.getTitle());

        verify(vibeFieldRepositoryPort).findAllById(fieldIds);
        verify(vibeRepositoryPort).save(any());
    }

    @Test
    void shouldUpdateVibeSuccessfully(){
        UUID id = UUID.randomUUID();
        UUID accountId = UUID.randomUUID();
        List<UUID> fieldIds = List.of(UUID.randomUUID());

        UpdateVibeCommand updateVibeCommand = new UpdateVibeCommand();
        updateVibeCommand.setId(id);
        updateVibeCommand.setAccountId(accountId);
        updateVibeCommand.setTitle("Updated Vibe");
        updateVibeCommand.setFieldIds(fieldIds);

        Vibe vibe = new Vibe();
        vibe.setId(id);
        vibe.setVibeAccountId(accountId);
        vibe.setTitle("Old Title");

        EmailField emailField = new EmailField();
        emailField.setEmail("test@gmail.com");
        List<VibeField> updatedFields = List.of(emailField);

        when(vibeRepositoryPort.findById(id)).thenReturn(Optional.of(vibe));
        when(vibeFieldRepositoryPort.findAllById(fieldIds)).thenReturn(updatedFields);

        when(vibeRepositoryPort.save(any())).thenAnswer(invocation->invocation.getArgument(0));

        VibeDto vibeDto = vibeService.update(updateVibeCommand);

        assertNotNull(vibeDto);
        assertEquals("Updated Vibe", vibeDto.getTitle());
        assertEquals(id,vibeDto.getId());

        verify(vibeRepositoryPort).findById(id);
        verify(vibeFieldRepositoryPort).findAllById(fieldIds);
        verify(vibeRepositoryPort).save(vibe);
    }

    @Test
    void shouldThrowWhenAccountDoesNotMatch(){
        UUID id = UUID.randomUUID();
        UUID realOwnerId = UUID.randomUUID();
        UUID someoneElseId = UUID.randomUUID();

        Vibe vibe = new Vibe();
        vibe.setId(id);
        vibe.setVibeAccountId(realOwnerId);

        when(vibeRepositoryPort.findById(id)).thenReturn(Optional.of(vibe));

        assertThrows(SecurityException.class,()->{vibeService.delete(id,someoneElseId);
        });

        verify(vibeRepositoryPort).findById(id);
        verify(vibeRepositoryPort,never()).delete(any());
    }

    @Test
    void shouldReturnVibeById(){
        UUID id = UUID.randomUUID();

        Vibe vibe = new Vibe();
        vibe.setId(id);
        vibe.setTitle("My Vibe");

        when(vibeRepositoryPort.findById(id)).thenReturn(Optional.of(vibe));

        VibeDto vibeDto = vibeService.getVibeById(id);

        assertNotNull(vibeDto);
        assertEquals(id,vibeDto.getId());
        assertEquals("My Vibe",vibeDto.getTitle());

        verify(vibeRepositoryPort).findById(id);
    }

    @Test
    void shouldReturnAllVibesByAccountId(){
        UUID accountId = UUID.randomUUID();

        Vibe vibe1 = new Vibe();
        vibe1.setId(UUID.randomUUID());
        vibe1.setVibeAccountId(accountId);
        vibe1.setTitle("Vibe 1");

        Vibe vibe2 = new Vibe();
        vibe2.setId(UUID.randomUUID());
        vibe2.setVibeAccountId(accountId);
        vibe2.setTitle("Vibe 2");

        List<Vibe> vibes = List.of(vibe1,vibe2);

        when(vibeRepositoryPort.findAllByAccountId(accountId)).thenReturn(vibes);

        List<VibeDto> result = vibeService.findAllByVibeAccountId(accountId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Vibe 1",result.get(0).getTitle());
        assertEquals("Vibe 2",result.get(1).getTitle());

        verify(vibeRepositoryPort).findAllByAccountId(accountId);
    }
}
