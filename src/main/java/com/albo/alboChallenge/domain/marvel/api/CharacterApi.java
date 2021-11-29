package com.albo.alboChallenge.domain.marvel.api;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterApi implements Serializable {

  private Long id;
  private String resourceURI;
  private String name;

}