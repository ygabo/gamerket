import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "gamerket"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "securesocial" %% "securesocial" % "master-SNAPSHOT"
  )

  //appDependencies.+("securesocial" %% "securesocial" % "2.0.12")

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // this is for securesocial
    resolvers += Resolver.url("sbt-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns)
  )

}
