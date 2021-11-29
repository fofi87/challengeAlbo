package com.albo.alboChallenge.domain.marvel.api;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollaboratorsListApi implements Serializable {

  public List<CollaboratorApi> items;

}
