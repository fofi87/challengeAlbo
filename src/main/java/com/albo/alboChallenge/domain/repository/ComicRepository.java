package com.albo.alboChallenge.domain.repository;

import com.albo.alboChallenge.domain.model.Comic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>, JpaSpecificationExecutor<Comic> {

  List<Comic> findByCharacters_name(String name);

}
