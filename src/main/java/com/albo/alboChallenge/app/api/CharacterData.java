package com.albo.alboChallenge.app.api;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharacterData {

  private String character;
  private List<String> comics;

}
