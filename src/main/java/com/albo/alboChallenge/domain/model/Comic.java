package com.albo.alboChallenge.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comic implements Serializable {

  @Id
  private Long id;
  private String title;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "comic_collaborator",
      joinColumns = @JoinColumn(name = "comic_id"),
      inverseJoinColumns = @JoinColumn(name = "collaborator_id")
  )
  private List<Collaborator> creators;

  @ManyToMany(cascade= CascadeType.ALL)
  @JoinTable(name = "comic_character",
      joinColumns = @JoinColumn(name = "comic_id"),
      inverseJoinColumns = @JoinColumn(name = "character_id")
  )
  private List<Character> characters;
}
