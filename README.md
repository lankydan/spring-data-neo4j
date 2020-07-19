To create a Neo4j docker container run the command below:

```shell script
docker run \
    --name neo4j \
    -p7474:7474 -p7687:7687 \
    -d \
    -v $HOME/neo4j/data:/data \
    -v $HOME/neo4j/logs:/logs \
    -v $HOME/neo4j/import:/var/lib/neo4j/import \
    -v $HOME/neo4j/plugins:/plugins \
    --env NEO4J_AUTH=neo4j/test \
    -e NEO4JLABS_PLUGINS=\[\"apoc\"\] neo4j:latest
```

To log into the Neo4j browser:

- Go to `localhost:7474`
- username = `neo4j`
- password = `test`

To insert the data needed for this example run the following statements in the Neo4j browser:

```cypher
CREATE (bruges:City {name:"bruges", latitude: 51.2605829, longitude: 3.0817189})
CREATE (brussels:City {name:"brussels", latitude: 50.854954, longitude: 4.3051786})
CREATE (paris:City {name:"paris", latitude: 48.8588376, longitude: 2.2773455})
CREATE (dresden:City {name:"dresden", latitude: 51.0767496, longitude: 13.6321595})
MERGE (bruges)-[:NEXT]->(brussels)
MERGE (brussels)-[:NEXT]->(dresden)
MERGE (brussels)-[:NEXT]->(paris)
MERGE (bruges)-[:NEXT]->(paris)
MERGE (paris)-[:NEXT]->(dresden)
```

To run the code in this repo either:

- Run the `Application` class' main method
- Run `mvn clean install spring-boot:repackage` and then run the jar created in the `target` directory
- Run `mvn spring-boot:run`