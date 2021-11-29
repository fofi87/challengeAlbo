package com.albo.alboChallenge.domain.marvel.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicResponseApi extends GenericResponse {

  private ComicDataApi data;
}
