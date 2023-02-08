# DKFDS
Scala.js-fasade for Det Fælles Designsystem.

Tilføjes til build.sbt:
```
lazy val DKFDS = crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Full).in(file("DKFDS"))
        .settings(
            libraryDependencies += "com.lihaoyi" %%% "scalatags" % "0.12.0"
        )
        .jsSettings(
            libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.3.0",
            Compile / npmDependencies ++= Seq(
                "dkfds" -> "latest"
            )
        )
        .jsConfigure { project => project.enablePlugins(ScalaJSBundlerPlugin) }
```
