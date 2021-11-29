package com.albo.alboChallenge.domain.service;

import com.albo.alboChallenge.domain.model.LastSync;
import java.util.Optional;

public interface LastSyncService {

  Optional<LastSync> getLastSync();
  void saveDate();

}
