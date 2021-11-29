package com.albo.alboChallenge.domain.repository;

import com.albo.alboChallenge.domain.model.Character;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {

  Optional<Character> findByName(String name);
}
