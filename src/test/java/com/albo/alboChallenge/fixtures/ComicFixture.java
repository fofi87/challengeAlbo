package com.albo.alboChallenge.fixtures;

import com.albo.alboChallenge.domain.model.Character;
import com.albo.alboChallenge.domain.model.Collaborator;
import com.albo.alboChallenge.domain.model.Comic;
import java.util.ArrayList;
import java.util.List;

public class ComicFixture {

  public static Comic getComicWithId(Long id) {
    List<Character> characters = new ArrayList<>();
    List<Comic> comics = new ArrayList<>();
    comics.add(Comic.builder().title("Title").id(56465L).build());
    characters.add(Character.builder().name("Avengers").id(123L).comics(comics).build());
    characters.add(Character.builder().name("Iron man").id(123L).comics(new ArrayList<>()).build());
    List<Collaborator> creators = new ArrayList<>();
    creators.add(Collaborator.builder().name("Felix").id(789L).role("editor").build());
    creators.add(Collaborator.builder().name("John").id(789L).role("writer").build());
    creators.add(Collaborator.builder().name("Charles").id(789L).role("colorist").build());
    return Comic.builder().id(id).characters(characters).creators(creators).build();
  }

  public static List<Comic> getComics() {
    List<Comic> comics = new ArrayList<>();
    comics.add(getComicWithId(1L));
    comics.add(getComicWithId(2L));
    return comics;
  }

}
