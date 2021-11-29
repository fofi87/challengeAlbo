package com.albo.alboChallenge.domain.service.impl;

import com.albo.alboChallenge.aspects.LogAnnotation;
import com.albo.alboChallenge.domain.model.LastSync;
import com.albo.alboChallenge.domain.repository.LastSyncRepository;
import com.albo.alboChallenge.domain.service.LastSyncService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@LogAnnotation
public class LastSyncServiceImpl implements LastSyncService {

  @Autowired
  private LastSyncRepository lastSyncRepository;

  private static final Long LAST_SYNC_ID = 1L;

  public Optional<LastSync> getLastSync() {
    return this.lastSyncRepository.findById(LAST_SYNC_ID);
  }

  @Override
  @Transactional
  public void saveDate() {
    LastSync lastSync = createLastSync();
    this.lastSyncRepository.save(lastSync);
  }

  private LastSync createLastSync() {
    Date now = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    String date = dateFormat.format(now);
    return LastSync.builder().id(LAST_SYNC_ID).date(date).build();
  }
}
