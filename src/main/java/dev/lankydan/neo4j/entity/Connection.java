package dev.lankydan.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity("NEXT")
public class Connection {

  @Id
  @GeneratedValue
  private long id;
  @StartNode
  @JsonIgnore
  private City start;
  @EndNode
  private City end;

  public Connection(long id, City start, City end) {
    this.id = id;
    this.start = start;
    this.end = end;
  }

  public Connection() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public City getStart() {
    return start;
  }

  public void setStart(City start) {
    this.start = start;
  }

  public City getEnd() {
    return end;
  }

  public void setEnd(City end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return "Connection{" +
        "id=" + id +
        ", start=" + start +
        ", end=" + end +
        '}';
  }
}
