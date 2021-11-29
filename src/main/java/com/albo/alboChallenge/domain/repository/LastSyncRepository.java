package com.albo.alboChallenge.domain.repository;

import com.albo.alboChallenge.domain.model.LastSync;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LastSyncRepository extends JpaRepository<LastSync, Long>, JpaSpecificationExecutor<LastSync> {

}
