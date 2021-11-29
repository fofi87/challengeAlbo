package com.albo.alboChallenge.domain.marvel.api;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDataApi extends GenericData {

  public List<CharacterApi> results;

}
