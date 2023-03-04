#!/bin/bash

echo "=========================="
echo "stopping arcade db"
echo "----------------"

docker container stop arcade-db



echo "=========================="
echo "remove old images"
echo "----------------"

docker container rm arcade-db

echo "=========================="
echo "Creating arcade db"
echo "----------------"


docker run --name arcade-db \
            --rm -p 2480:2480 -p 2424:2424 \
           -e JAVA_OPTS="-Darcadedb.server.rootPassword=playwithdata -Darcadedb.server.defaultDatabases=Imported[root]{import:https://github.com/ArcadeData/arcadedb-datasets/raw/main/orientdb/OpenBeer.gz}"\
           -d arcadedata/arcadedb:latest


echo "=========================="
echo "launch gradle"
echo "----------------"
sleep 1

./gradlew clean build
echo "=========================="
echo "local env was created"
echo "----------------"