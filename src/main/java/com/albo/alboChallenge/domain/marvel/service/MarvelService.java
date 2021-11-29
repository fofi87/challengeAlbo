package com.albo.alboChallenge.domain.marvel.service;

import com.albo.alboChallenge.domain.marvel.api.CharacterResponseApi;
import com.albo.alboChallenge.domain.marvel.api.ComicResponseApi;
import com.albo.alboChallenge.domain.model.LastSync;

public interface MarvelService {

  CharacterResponseApi getCharacterByName(String name);
  ComicResponseApi getComicsByCharacter(Long id, Long offset, LastSync lastSync);

}
