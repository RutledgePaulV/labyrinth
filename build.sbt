name := "labyrinth"

version := "1.0"

scalaVersion := "2.11.7"

resolvers ++= Seq(
    "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
    "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq("com.storm-enroute" %% "macrogl" % "0.4-SNAPSHOT")
