package com.albo.alboChallenge.domain.marvel.service.impl;

import com.albo.alboChallenge.aspects.LogAnnotation;
import com.albo.alboChallenge.domain.marvel.api.CharacterResponseApi;
import com.albo.alboChallenge.domain.marvel.api.ComicResponseApi;
import com.albo.alboChallenge.domain.marvel.service.MarvelService;
import com.albo.alboChallenge.domain.model.LastSync;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@LogAnnotation
public class MarvelServiceImpl implements MarvelService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${keyPrivate}")
  private String keyPrivate;
  @Value("${keyPublic}")
  private String keyPublic;

  @Value("${baseUrl}")
  private String baseUrl;
  @Value("${pathCharacters}")
  private String pathCharacters;
  @Value("${pathComics}")
  private String pathComics;

  SimpleDateFormat fromBd = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
  SimpleDateFormat fromService = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

  @Override
  public CharacterResponseApi getCharacterByName(String name) {
    ResponseEntity<CharacterResponseApi> response = this.restTemplate.getForEntity(getUrl(pathCharacters + "?name=" + name + "&", 0L), CharacterResponseApi.class);
    return response.getBody();
  }

  @Override
  public ComicResponseApi getComicsByCharacter(Long id, Long offset, LastSync lastSync) {
    String path = pathComics.replaceFirst(":id", id.toString()) + "?";
    if(lastSync != null) {
      try {
         path = path + "modifiedSince=" + fromService.format(fromBd.parse(lastSync.getDate())) + "&";
      } catch (ParseException e) {
        new Exception("Format date error");
      }
    }
    ResponseEntity<ComicResponseApi> response = this.restTemplate.getForEntity(getUrl(path, offset), ComicResponseApi.class);
    return response.getBody();
  }

  private String getUrl(String path, Long offset) {
    String ts = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String md5 = getMD5(ts);
    return baseUrl + path + "limit=100" + "&offset=" + offset + "&apikey=" + keyPublic + "&ts=" + ts + "&hash=" + md5;
  }

  private String getMD5(String ts) {
    String hash = ts + keyPrivate + keyPublic;
    String md5 = DigestUtils.md5Hex(hash);
    return md5;
  }
}
