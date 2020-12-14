inThisBuild(
  List(
    organization := "ba.sake",
    scalaVersion := "2.13.3",
    scalafmtOnCompile := true,
    resolvers += Resolver.sonatypeRepo("snapshots")
  )
)

val hepekVersion = "0.8.8"

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek-play" % hepekVersion
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
      "ba.sake" %%% "rxtags" % "0.0.6",
      "ba.sake" %%% "scalajs-router" % "0.0.9",
      "ba.sake" %%% "hepek-components" % hepekVersion
    ),
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv()
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(shared.js)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(
    Compile / scalacOptions ++= List("-Ymacro-annotations", "-deprecation"),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %%% "play-json" % "2.9.0",
      "ba.sake" %%% "stone-macros" % "0.5.0",
      "io.scalaland" %%% "chimney" % "0.5.3",
      "org.scalatest" %%% "scalatest" % "3.2.0" % "test"
    )
  )
