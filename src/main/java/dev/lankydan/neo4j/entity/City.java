package dev.lankydan.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonInclude(NON_EMPTY)
@NodeEntity(value = "City")
public class City {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private double longitude;
  private double latitude;

  @Relationship(type = "NEXT")
  private Set<Connection> connections = Collections.emptySet();

  public City(
      Long id,
      String name,
      double longitude,
      double latitude,
      Set<Connection> connections
  ) {
    this.id = id;
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
    this.connections = connections;
  }

  public City(
      Long id,
      String name,
      double longitude,
      double latitude
  ) {
    this.id = id;
    this.name = name;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public City() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public Set<Connection> getConnections() {
    return connections;
  }

  // prevents the stack overflow when serializing to json
  @JsonGetter("connections")
  public Set<String> getJsonConnections() {
    return connections.stream().map(c -> c.getEnd().name).collect(Collectors.toSet());
  }

  public void setConnections(Set<Connection> connections) {
    this.connections = connections;
  }

  @Override
  public String toString() {
    return "City{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", longitude="
        + longitude
        + ", latitude="
        + latitude
        + ", connections="
        + connections.stream().map(c -> c.getEnd().name).collect(Collectors.toList())
        + '}';
  }
}
