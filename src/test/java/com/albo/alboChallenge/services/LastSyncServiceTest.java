package com.albo.alboChallenge.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.albo.alboChallenge.domain.model.LastSync;
import com.albo.alboChallenge.domain.repository.LastSyncRepository;
import com.albo.alboChallenge.domain.service.impl.LastSyncServiceImpl;
import com.albo.alboChallenge.fixtures.LastSyncFixture;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class LastSyncServiceTest {

  @InjectMocks
  private LastSyncServiceImpl lastSyncService;

  @Mock
  private LastSyncRepository lastSyncRepository;

  @Test
  public void getLastSyncTest() {
    LastSync lastSync = LastSyncFixture.getLastSync();
    when(this.lastSyncRepository.findById(1L)).thenReturn(Optional.of(lastSync));

    Optional<LastSync> response = this.lastSyncService.getLastSync();
    assertEquals(lastSync, response.get());
    verify(this.lastSyncRepository, times(1)).findById(1L);
  }

  @Test
  public void saveDateTest() {
    LastSync lastSync = LastSyncFixture.getLastSync();
    when(this.lastSyncRepository.save(any(LastSync.class))).thenReturn(lastSync);

    this.lastSyncService.saveDate();
    verify(this.lastSyncRepository, times(1)).save(any(LastSync.class));
  }

}
