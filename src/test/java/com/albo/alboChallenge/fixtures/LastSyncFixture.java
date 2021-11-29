package com.albo.alboChallenge.fixtures;

import com.albo.alboChallenge.domain.model.LastSync;

public class LastSyncFixture {

  public static LastSync getLastSync() {
    return LastSync.builder().date("01/12/2021").id(1L).build();
  }

}
