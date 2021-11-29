package com.albo.alboChallenge.domain.service;

import com.albo.alboChallenge.app.api.CharactersResponse;
import com.albo.alboChallenge.app.api.CollaboratorsResponse;

public interface ComicService {

  CollaboratorsResponse findCollaboratorsByCharacters_name(String name);
  CharactersResponse findCharactersByCharacters_name(String name);

}
