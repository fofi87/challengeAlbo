package com.albo.alboChallenge.domain.marvel.api;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollaboratorApi implements Serializable {

  private String resourceURI;
  private String name;
  private String role;

}