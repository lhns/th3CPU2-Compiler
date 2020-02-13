inThisBuild(Seq(
  name := "th3CPU2-Compiler",
  version := "0.0.1",

  scalaVersion := "2.13.1",
))

name := (ThisBuild / name).value

def settings: Seq[SettingsDefinition] = Seq(
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),

  assembly / assemblyJarName := s"${name.value}-${version.value}.sh.bat",

  assembly / assemblyOption := (assembly / assemblyOption).value
    .copy(prependShellScript = Some(AssemblyPlugin.defaultUniversalScript(shebang = false))),

  assembly / assemblyMergeStrategy := {
    case PathList("module-info.class") =>
      MergeStrategy.discard

    case PathList("META-INF", "jpms.args") =>
      MergeStrategy.discard

    case PathList("META-INF", "io.netty.versions.properties") =>
      MergeStrategy.first

    case x =>
      val oldStrategy = (assembly / assemblyMergeStrategy).value
      oldStrategy(x)
  }
)

lazy val root = project.in(file("."))
  .settings(
    publishArtifact := false
  )
  .aggregate(compiler)

lazy val compiler = project
  .settings(settings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % "2.1.0",
      "org.scodec" %% "scodec-bits" % "1.1.13",
      "com.lihaoyi" %% "fastparse" % "2.2.2"
    )
  )
