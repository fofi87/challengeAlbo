package com.albo.alboChallenge.domain.marvel.service.impl;

import com.albo.alboChallenge.aspects.LogAnnotation;
import com.albo.alboChallenge.domain.marvel.api.CharacterResponseApi;
import com.albo.alboChallenge.domain.marvel.api.ComicApi;
import com.albo.alboChallenge.domain.marvel.api.ComicResponseApi;
import com.albo.alboChallenge.domain.marvel.service.MarvelService;
import com.albo.alboChallenge.domain.marvel.service.SincronizeService;
import com.albo.alboChallenge.domain.model.Collaborator;
import com.albo.alboChallenge.domain.model.Comic;
import com.albo.alboChallenge.domain.model.LastSync;
import com.albo.alboChallenge.domain.repository.ComicRepository;
import com.albo.alboChallenge.domain.service.LastSyncService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@LogAnnotation
public class SincronizeServiceImpl implements SincronizeService {

  @Autowired
  private MarvelService marvelService;

  @Autowired
  private ComicRepository comicRepository;

  @Autowired
  private LastSyncService lastSyncService;

  private static final String IRON_MAN = "Iron Man";
  private static final String CAPTAIN_AMERICA = "Captain America";

  /**
   * Runs at 00:00:01 of every day
   */
  @Scheduled(cron = "1 0 0 * * ?")
  public void sincronizeCharacterInfo() {
    CharacterResponseApi ironMAn = this.marvelService.getCharacterByName(IRON_MAN);
    this.sincronizeCharacter(ironMAn);
    CharacterResponseApi captainAmerica = this.marvelService.getCharacterByName(CAPTAIN_AMERICA);
    this.sincronizeCharacter(captainAmerica);
    this.lastSyncService.saveDate();
  }

  @Override
  @Transactional
  public void saveData(List<Comic> lsComics) {
    this.comicRepository.saveAll(lsComics);
  }

  private void sincronizeCharacter(CharacterResponseApi characterResponseApi) {
    Long offset = 0L;
    List<ComicApi> lsComicApis = new ArrayList<>();
    Optional<LastSync> lastSync = this.lastSyncService.getLastSync();
    getMarvelComics(characterResponseApi, offset, lsComicApis, lastSync);

    List<Comic> comics = lsComicApis.stream().map(comicApi ->
        Comic.builder()
            .id(comicApi.getId())
            .title(comicApi.getTitle())
            .creators(comicApi.getCreators().getItems().stream().map(collaboratorApi ->
                Collaborator.builder()
                    .name(collaboratorApi.getName())
                    .role(collaboratorApi.getRole())
                    .resourceURI(collaboratorApi.getResourceURI())
                    .id(getIdFromUri(collaboratorApi.getResourceURI()))
                    .build()).collect(Collectors.toList()))
            .characters(comicApi.getCharacters().getItems().stream().map(characterApi ->
                com.albo.alboChallenge.domain.model.Character.builder()
                    .name(characterApi.getName())
                    .id(getIdFromUri(characterApi.getResourceURI()))
                    .resourceURI(characterApi.getResourceURI())
                    .build()).collect(Collectors.toList()))
            .build())
        .collect(Collectors.toList());

    this.saveData(comics);
  }

  private void getMarvelComics(CharacterResponseApi characterResponseApi, Long offset, List<ComicApi> lsComicApis, Optional<LastSync> lastSync) {
    ComicResponseApi comicResponseApi;
    do {
      comicResponseApi = this.marvelService.getComicsByCharacter(
          characterResponseApi.getData().getResults().get(0).getId(), offset, lastSync.isPresent() ? lastSync.get() : null);
      offset += comicResponseApi.getData().getCount();
      lsComicApis.addAll(comicResponseApi.getData().getResults());
    } while (offset < comicResponseApi.getData().getTotal());
  }

  private Long getIdFromUri(String uri) {
    String[] splited = uri.split("/");
    return Long.parseLong(splited[splited.length - 1]);
  }

}
