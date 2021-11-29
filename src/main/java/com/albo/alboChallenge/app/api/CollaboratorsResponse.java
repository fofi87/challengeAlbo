package com.albo.alboChallenge.app.api;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CollaboratorsResponse {

  private String last_sync;
  private List<String> editors;
  private List<String> writers;
  private List<String> colorists;

}
