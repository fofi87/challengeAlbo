package com.albo.alboChallenge.app.api;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharactersResponse {

  private String last_sync;
  private List<CharacterData> characters;

}
