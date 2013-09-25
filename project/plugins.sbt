// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.0")

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

addSbtPlugin("net.litola" % "play-sass" % "0.3.0")
