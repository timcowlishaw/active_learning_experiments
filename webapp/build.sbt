import org.scalatra.sbt._

import com.typesafe.sbt.SbtStartScript

import org.scalatra.sbt.PluginKeys._

name := "Active Reuters"

version := "0.1"

scalaVersion := "2.10.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Wolfe Release" at "http://homeniscient.cs.ucl.ac.uk:8081/nexus/content/repositories/releases"

resolvers += "IESL Release" at "http://dev-iesl.cs.umass.edu/nexus/content/groups/public"

resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "ml.wolfe" %% "wolfe-core" % "0.3.0",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalatra" %% "scalatra" % "2.3.0",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.7.v20120910" % "container;compile",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container;compile;provided;test" artifacts Artifact("javax.servlet", "jar", "jar")
)

seq(ScalatraPlugin.scalatraWithJRebel : _*)

seq(scalateSettings : _*)

seq(SbtStartScript.startScriptForClassesSettings: _*)

mainClass := Some("active_reuters.Launcher")
