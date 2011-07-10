import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {
	val scalaTest = "org.scalatest" % "scalatest" % "1.4.RC2"
	val netty = "org.jboss.netty" % "netty" % "3.2.4.Final"
}
