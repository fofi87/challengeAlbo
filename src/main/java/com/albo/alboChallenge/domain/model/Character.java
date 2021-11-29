package com.albo.alboChallenge.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Character implements Serializable {

  @Id
  private Long id;
  private String resourceURI;
  private String name;

  @ManyToMany(mappedBy = "characters")
  private List<Comic> comics;

}
