#!/bin/bash

echo "=========================="
echo "stopping arcade db"
echo "----------------"

docker container stop arcade-db



echo "=========================="
echo "remove old images arcade db"
echo "----------------"

docker container rm arcade-db

echo "=========================="
echo "Creating arcade db"
echo "----------------"


docker run --name arcade-db \
            --rm -p 2480:2480 -p 2424:2424 -p 8182:8182 -p 6379:6379 -p 5432:5432 \
           -e JAVA_OPTS="-Darcadedb.server.rootPassword=playwithdata
                          -Darcadedb.server.plugins=GremlinServer:com.arcadedb.server.gremlin.GremlinServerPlugin
                          -Darcadedb.server.defaultDatabases=Imported[root]{import:https://github.com/ArcadeData/arcadedb-datasets/raw/main/orientdb/OpenBeer.gz}"\
           -d arcadedata/arcadedb:22.8.1


echo "=========================="
echo "launch gradle"
echo "----------------"
sleep 1

./gradlew clean build
echo "=========================="
echo "local env was created"
echo "----------------"