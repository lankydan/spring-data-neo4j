package dev.lankydan.neo4j.repository;

import dev.lankydan.neo4j.entity.City;
import dev.lankydan.neo4j.entity.Path;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.support.SimpleNeo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//public interface PathRepository extends Neo4jRepository<City, Long> {
//
//  @Query("MATCH (a:City {name:$departure}), (b:City {name:$arrival})\n"
//      + "MATCH p=(a)-[*]->(b)\n"
//      + "WITH collect(p) as paths\n"
//      + "CALL apoc.spatial.sortByDistance(paths) YIELD path, distance\n"
//      + "RETURN path, distance")
//  List<Path> getAllPaths(String departure, String arrival);
//}


@Repository
public class PathRepository extends SimpleNeo4jRepository<City, Long> {

  private static final Logger log = LoggerFactory.getLogger(PathRepository.class);

  private static final String GET_ALL_PATHS_QUERY =
      "MATCH (a:City {name:$departure}), (b:City {name:$arrival})\n"
          + "MATCH p=(a)-[*]->(b)\n"
          + "WITH collect(p) as paths\n"
          + "CALL apoc.spatial.sortByDistance(paths) YIELD path, distance\n"
          + "RETURN path, distance";

  private final Session session;

  public PathRepository(Session session) {
    super(City.class, session);
    this.session = session;
  }

  public List<Path> getAllPaths(String departure, String arrival) {
    Map<String, String> parameters = Map.of(
        "departure", departure,
        "arrival", arrival
    );

    Result rows = session.query(GET_ALL_PATHS_QUERY, parameters);

    List<Path> results = new ArrayList<>();
    for (Map<String, Object> row : rows) {
      results.add(convertRow(row));
    }

    return results;
  }

  private Path convertRow(Map<String, Object> row) {
    // paths is an iterable allows a stream or for loop to be used
    InternalPath.SelfContainedSegment[] connections =
        (InternalPath.SelfContainedSegment[]) row.get("path");

    List<City> cities = new ArrayList<>();
    for (InternalPath.SelfContainedSegment connection : connections) {
      cities.add(convert(connection));
    }

    double distance = (Double) row.get("distance");
    return new Path(cities, distance);
  }

  private City convert(InternalPath.SelfContainedSegment connection) {
    log.info("connection: {}", connection);
    return new City(
        connection.end().id(),
        connection.end().get("name").asString(),
        connection.end().get("latitude").asDouble(),
        connection.end().get("longitude").asDouble()
    );
  }
}
