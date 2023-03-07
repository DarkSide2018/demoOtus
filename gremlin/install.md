Gremlin console

Adapt the version number. Here is an example for TinkerPop 3.6.2:
Build

docker build -f ./Dockerfile-gremlin-console -t gremlin-console:3.6.2 --build-arg tinkerpopVersion=3.6.2 [--build-arg https_proxy=$https_proxy] .

Run

docker run -it --rm gremlin-console:3.6.2


after that perfrom command in gremlin console

:remote connect tinkerpop.server arcade-db-remote.yaml session

