inThisBuild(
  List(
    organization := "ba.sake",
    scalaVersion := "2.13.3",
    Compile / scalacOptions ++= List("-Ymacro-annotations", "-deprecation"),
    resolvers += Resolver.bintrayRepo("stg-tud", "maven")
  )
)

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek-play" % "0.8.0"
    ),
    scalaJSProjects := Seq(client), // "depends" on client JS code
    pipelineStages in Assets := Seq(scalaJSPipeline) // copies JS code to Play server assets
  )
  .enablePlugins(PlayScala, SbtWeb)
  .dependsOn(shared.jvm)

lazy val client = (project in file("client"))
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "de.tuda.stg" %%% "rescala" % "0.30.0",
      "ba.sake" %%% "scalajs-router" % "0.0.5",
      "ba.sake" %%% "hepek-components" % "0.8.0"
    ),
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.play" %%% "play-json" % "2.9.0",
      "ba.sake" %%% "stone-macros" % "0.4.0",
      "org.scalatest" %%% "scalatest" % "3.2.0" % "test"
    )
  )
