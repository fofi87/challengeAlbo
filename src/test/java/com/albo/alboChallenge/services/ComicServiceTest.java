package com.albo.alboChallenge.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.albo.alboChallenge.app.api.CharactersResponse;
import com.albo.alboChallenge.app.api.CollaboratorsResponse;
import com.albo.alboChallenge.domain.exceptions.GenericNotFoundException;
import com.albo.alboChallenge.domain.model.Character;
import com.albo.alboChallenge.domain.model.Comic;
import com.albo.alboChallenge.domain.model.LastSync;
import com.albo.alboChallenge.domain.repository.CharacterRepository;
import com.albo.alboChallenge.domain.repository.ComicRepository;
import com.albo.alboChallenge.domain.service.impl.ComicServiceImpl;
import com.albo.alboChallenge.domain.service.impl.LastSyncServiceImpl;
import com.albo.alboChallenge.fixtures.CharacterFixture;
import com.albo.alboChallenge.fixtures.ComicFixture;
import com.albo.alboChallenge.fixtures.LastSyncFixture;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ComicServiceTest {

  @InjectMocks
  private ComicServiceImpl comicService;

  @Mock
  private ComicRepository comicRepository;

  @Mock
  private LastSyncServiceImpl lastSyncService;

  @Mock
  private CharacterRepository characterRepository;

  @Test
  public void findCollaboratorsByCharacters_name_Success_Test() {
    Character character = CharacterFixture.getCharacter();
    LastSync lastSync = LastSyncFixture.getLastSync();
    List<Comic> comics = ComicFixture.getComics();
    when(this.characterRepository.findByName("Iron man")).thenReturn(Optional.of(character));
    when(this.lastSyncService.getLastSync()).thenReturn(Optional.of(lastSync));
    when(this.comicRepository.findByCharacters_name("Iron man")).thenReturn(comics);

    CollaboratorsResponse response = comicService.findCollaboratorsByCharacters_name("Iron man");

    assertTrue(!response.getColorists().isEmpty());
    assertTrue(!response.getEditors().isEmpty());
    assertTrue(!response.getWriters().isEmpty());
    verify(this.characterRepository, times(1)).findByName("Iron man");
    verify(this.lastSyncService, times(1)).getLastSync();
    verify(this.comicRepository, times(1)).findByCharacters_name("Iron man");
  }

  @Test
  public void findCollaboratorsByCharacters_name_NotFound_Test() {
    GenericNotFoundException genericNotFoundException = new GenericNotFoundException("Character not found");
    doThrow(genericNotFoundException).when(this.characterRepository).findByName("Iron Man");

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      comicService.findCollaboratorsByCharacters_name("Iron man");
    });

    assertTrue(exception.getMessage().contains("Character not found"));
    verify(this.characterRepository, times(1)).findByName("Iron man");
    verify(this.lastSyncService, times(0)).getLastSync();
    verify(this.comicRepository, times(0)).findByCharacters_name("Iron man");
  }

  @Test
  public void findCharactersByCharacters_name_Succes_Test() {
    Character character = CharacterFixture.getCharacter();
    LastSync lastSync = LastSyncFixture.getLastSync();
    List<Comic> comics = ComicFixture.getComics();
    when(this.characterRepository.findByName("Iron man")).thenReturn(Optional.of(character));
    when(this.lastSyncService.getLastSync()).thenReturn(Optional.of(lastSync));
    when(this.comicRepository.findByCharacters_name("Iron man")).thenReturn(comics);

    CharactersResponse response = comicService.findCharactersByCharacters_name("Iron man");

    assertTrue(!response.getCharacters().isEmpty());
    verify(this.characterRepository, times(1)).findByName("Iron man");
    verify(this.lastSyncService, times(1)).getLastSync();
    verify(this.comicRepository, times(1)).findByCharacters_name("Iron man");
  }

  @Test
  public void findCharactersByCharacters_name_Notfound_Test() {
    GenericNotFoundException genericNotFoundException = new GenericNotFoundException("Character not found");
    doThrow(genericNotFoundException).when(this.characterRepository).findByName("Iron Man");

    Exception exception = assertThrows(GenericNotFoundException.class, () -> {
      comicService.findCharactersByCharacters_name("Iron man");
    });

    assertTrue(exception.getMessage().contains("Character not found"));
    verify(this.characterRepository, times(1)).findByName("Iron man");
    verify(this.lastSyncService, times(0)).getLastSync();
    verify(this.comicRepository, times(0)).findByCharacters_name("Iron man");
  }

}
