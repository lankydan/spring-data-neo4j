package dev.lankydan.neo4j.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult // annotation only needed if using an inferred query
public class Path {
  public List<City> path;
  public double distance;

  public Path(List<City> path, double distance) {
    this.path = path;
    this.distance = distance;
  }

  public List<City> getPath() {
    return path;
  }

  public void setPath(List<City> path) {
    this.path = path;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  @Override
  public String toString() {
    return "Path{" +
        "path=" + path +
        ", distance=" + distance +
        '}';
  }
}
