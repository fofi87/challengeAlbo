package com.albo.alboChallenge.domain.service.impl;

import com.albo.alboChallenge.app.api.CharacterData;
import com.albo.alboChallenge.app.api.CharactersResponse;
import com.albo.alboChallenge.app.api.CollaboratorsResponse;
import com.albo.alboChallenge.aspects.LogAnnotation;
import com.albo.alboChallenge.domain.exceptions.GenericNotFoundException;
import com.albo.alboChallenge.domain.model.Collaborator;
import com.albo.alboChallenge.domain.model.Comic;
import com.albo.alboChallenge.domain.model.LastSync;
import com.albo.alboChallenge.domain.repository.CharacterRepository;
import com.albo.alboChallenge.domain.repository.ComicRepository;
import com.albo.alboChallenge.domain.service.ComicService;
import com.albo.alboChallenge.domain.service.LastSyncService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@LogAnnotation
public class ComicServiceImpl implements ComicService {

  @Autowired
  private ComicRepository comicRepository;

  @Autowired
  private LastSyncService lastSyncService;

  @Autowired
  private CharacterRepository characterRepository;

  @Override
  public CollaboratorsResponse findCollaboratorsByCharacters_name(String name) {
    this.characterRepository.findByName(name).orElseThrow(() -> new GenericNotFoundException("Character not found"));
    Optional<LastSync> lastSync = this.lastSyncService.getLastSync();
    List<Comic> comics = this.comicRepository.findByCharacters_name(name);
    return CollaboratorsResponse.builder()
            .last_sync(lastSync.get().getDate())
            .colorists(comics.stream().map(Comic::getCreators).flatMap(
                Collection::stream).filter(collaborator -> collaborator.getRole().equals("colorist")).distinct().map(Collaborator::getName).collect(Collectors.toList()))
            .writers(comics.stream().map(Comic::getCreators).flatMap(
                Collection::stream).filter(collaborator -> collaborator.getRole().equals("writer")).distinct().map(Collaborator::getName).collect(Collectors.toList()))
            .editors(comics.stream().map(Comic::getCreators).flatMap(
                Collection::stream).filter(collaborator -> collaborator.getRole().equals("editor")).distinct().map(Collaborator::getName).collect(Collectors.toList()))
            .build();
  }

  @Override
  public CharactersResponse findCharactersByCharacters_name(String name) {
    this.characterRepository.findByName(name).orElseThrow(() -> new GenericNotFoundException("Character not found"));
    Optional<LastSync> lastSync = this.lastSyncService.getLastSync();
    List<Comic> comics = this.comicRepository.findByCharacters_name(name);
    return CharactersResponse.builder()
            .last_sync(lastSync.get().getDate())
            .characters(comics.stream().map(Comic::getCharacters).flatMap(
              Collection::stream).filter(character -> !character.getName().equals(name)).distinct().map(
                character -> CharacterData.builder()
                  .character(character.getName())
                  .comics(character.getComics().stream().map(Comic::getTitle).collect(Collectors.toList()))
                  .build()).collect(Collectors.toList()))
            .build();
  }
}
