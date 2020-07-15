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

To run the code in this repo either:

- Run the `Application` class' main method
- Run `mvn clean install spring-boot:repackage` and then run the jar created in the `target` directory
- Run `mvn spring-boot:run`