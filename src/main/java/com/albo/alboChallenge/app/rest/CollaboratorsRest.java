package com.albo.alboChallenge.app.rest;

import com.albo.alboChallenge.app.api.CollaboratorsResponse;
import com.albo.alboChallenge.domain.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController for colaborators
 */
@RestController
@RequestMapping(path = "/colaborators")
public class CollaboratorsRest {

  @Autowired
  private ComicService comicService;

  @GetMapping("/{name}")
  public ResponseEntity<CollaboratorsResponse> getCollaborators(@PathVariable("name") String name){
    return new ResponseEntity<>(this.comicService.findCollaboratorsByCharacters_name(name), HttpStatus.OK);
  }

}
