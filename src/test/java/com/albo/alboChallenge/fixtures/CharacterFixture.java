package com.albo.alboChallenge.fixtures;

import com.albo.alboChallenge.domain.model.Character;

public class CharacterFixture {

  public static Character getCharacter() {
    return Character.builder().id(1L).name("Iron man").build();
  }

}
