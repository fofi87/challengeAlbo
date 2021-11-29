package com.albo.alboChallenge.domain.marvel.api;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicDataApi extends GenericData {

  public List<ComicApi> results;

}
