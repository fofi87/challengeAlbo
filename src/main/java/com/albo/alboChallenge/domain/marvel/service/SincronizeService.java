package com.albo.alboChallenge.domain.marvel.service;

import com.albo.alboChallenge.domain.model.Comic;
import java.util.List;

public interface SincronizeService {

  void sincronizeCharacterInfo();
  void saveData(List<Comic> lsComics);

}
