# Playful Scala

Simplistic full-stack Scala.  

Server side:
- Play Framework

Client side:
- ScalaJS
- ReScala

Shared:
- Stone routes
- Hepek templates

## Run app

1. start sbt with `sbt`
1. compile ScalaJS code with `client/fastOptJS`
1. run server with `server/run`
1. open http://localhost:9000 and enjoy! :)


## Run tests
For testing ScalaJS code you need to install `jsdom`:
```bash
npm install jsdom
```

