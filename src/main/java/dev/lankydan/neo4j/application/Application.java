package dev.lankydan.neo4j.application;

import dev.lankydan.neo4j.entity.City;
import dev.lankydan.neo4j.entity.Path;
import dev.lankydan.neo4j.repository.CityRepository;
import dev.lankydan.neo4j.repository.PathRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication(scanBasePackages = "dev.lankydan.neo4j")
@EnableNeo4jRepositories(basePackages = "dev.lankydan.neo4j")
// not doing this will cause index out of bounds error as it cannot find the entity
@EntityScan(basePackages = "dev.lankydan.neo4j")
public class Application implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  @Autowired
  private PathRepository pathRepository;
  @Autowired
  private CityRepository cityRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Override
  public void run(String... args) {
    for (Path path : pathRepository.getAllPaths("bruges", "dresden")) {
      log.info("Path: {}", path);
    }
    for (City city : cityRepository.findAll()) {
      log.info("City: {}", city);
    }
  }
}
