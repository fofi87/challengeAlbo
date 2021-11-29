package com.albo.alboChallenge.domain.marvel.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponseApi extends GenericResponse {

  private CharacterDataApi data;

}
