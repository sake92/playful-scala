# Playful Scala

Full-stack Scala with Play, ScalaJS, Hepek templates, Stone routes.

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





### sbt-web
If you need sass, gzip, uglify or other sbt-web stuff, you can use this plugin:
https://github.com/vmunier/sbt-web-scalajs