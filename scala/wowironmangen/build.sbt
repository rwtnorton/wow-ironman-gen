import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github"
ThisBuild / organizationName := "rwtnorton"

lazy val root = (project in file("."))
  .settings(
    name := "WowIronmanGen",
    mainClass in Compile := Some("wow_ironman_gen.Main"),
    libraryDependencies += scalaTest % Test
  )

assemblyMergeStrategy in assembly ~= ( (old) =>
  {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
  }
)
