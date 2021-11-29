package com.albo.alboChallenge.domain.marvel.api;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicApi implements Serializable {

  private Long id;
  private String title;
  private CollaboratorsListApi creators;
  private CharacterListApi characters;
}