package dev.lankydan.neo4j.repository;

import dev.lankydan.neo4j.entity.City;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CityRepository extends Neo4jRepository<City, Long> {

//  @Override
//  @Query("MATCH (n:City) RETURN n LIMIT 25")
//  Iterable<City> findAll();
}
