package dev.lankydan.neo4j.web;

import dev.lankydan.neo4j.entity.Path;
import dev.lankydan.neo4j.repository.PathRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paths")
public class PathController {

  private final PathRepository pathRepository;

  public PathController(PathRepository pathRepository) {
    this.pathRepository = pathRepository;
  }

  @GetMapping
  public ResponseEntity<List<Path>> get(@RequestParam String departure, @RequestParam String arrival) {
    return ResponseEntity.ok(pathRepository.getAllPaths(departure, arrival));
  }
}
