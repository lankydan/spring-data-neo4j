package dev.lankydan.neo4j.web;

import dev.lankydan.neo4j.entity.City;
import dev.lankydan.neo4j.entity.Path;
import dev.lankydan.neo4j.repository.CityRepository;
import dev.lankydan.neo4j.repository.PathRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

  private final CityRepository cityRepository;

  public CityController(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @GetMapping
  public ResponseEntity<Iterable<City>> get() {
    return ResponseEntity.ok(cityRepository.findAll());
  }
}
