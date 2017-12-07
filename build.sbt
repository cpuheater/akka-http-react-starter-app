
lazy val projectSettings = Seq(
 name := "akka-http-react-starter-app",
 version := "0.1",
 scalaVersion := "2.11.8",
 resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

lazy val `starter` = (project in file("."))
  .aggregate(`backend`)

lazy val `backend` = project
  .settings(projectSettings: _*)
  .settings(libraryDependencies++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.4.14",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.8",
    "com.typesafe.akka" %% "akka-http" % "10.0.0",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.0",
    "org.scalaz"        %%   "scalaz-core"        % "7.1.3",
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    "org.scalikejdbc" %% "scalikejdbc"        % "3.1.+",
    "com.h2database"  %  "h2"                 % "1.4.+"
  ))
