inThisBuild(
  List(
    organization := "ba.sake",
    scalaVersion := "2.13.3",
    Compile / scalacOptions ++= List("-Ymacro-annotations")
  )
)

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "ba.sake" %% "hepek-play" % "0.8.0",
      "org.scalatest" %% "scalatest" % "3.2.0" % "test"
    )
  )
  .enablePlugins(PlayScala)
  .dependsOn(shared.jvm)

lazy val client = (project in file("client"))
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "org.scalatest" %%% "scalatest" % "3.2.0" % "test"
    ),
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.play" %%% "play-json" % "2.9.0",
      "ba.sake" %%% "stone-macros" % "0.2.1"
    )
  )
