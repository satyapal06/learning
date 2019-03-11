name := "ScalaTutorials"

version := "1.0"

scalaVersion := "2.12.3"

lazy val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.typesafe"          %  "config"           % "1.2.1",
  "org.elasticsearch"     %  "elasticsearch"    % "2.4.1",
  "com.alibaba"           %  "fastjson"         % "1.2.13",
  "com.typesafe.akka"     %% "akka-actor"       % akkaVersion,
  "com.typesafe.akka"     %% "akka-remote"      % akkaVersion,
  "com.typesafe.akka"     %% "akka-slf4j"       % akkaVersion,
  "ch.qos.logback"        %  "logback-classic"  % "1.2.3",
  "org.scalatest"         %% "scalatest"        % "3.0.1" % "test",
  "com.typesafe.akka"     %% "akka-testkit"     % akkaVersion % Test
)

parallelExecution in Test := false