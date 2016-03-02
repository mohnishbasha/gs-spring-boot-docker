complete example:
- datadog statsd integration

Files changed:
- pom.xml - added dependency for dogstatds library
- Application.java - Initialized statsd and pushed telemetry

Steps:
- Reference tutorial:
http://containertutorials.com/docker-compose/spring-boot-app.html

Step 1
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
$ java -version
java version "1.8.0_45"
Java(TM) SE Runtime Environment (build 1.8.0_45-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)
$ sudo apt-get install gradle
$ sudo apt-get install maven

Step 2:
$git clone https://github.com/mohnishbasha/gs-spring-boot-docker.git

Step 3:
cd complete

Step 4:
setup proxies in mvn, if needed at ~/.m2/settings.xml

Step 5:
mvn package docker:build

Step 6: Verify image
$ docker images
REPOSITORY                        TAG                 IMAGE ID            CREATED             VIRTUAL SIZE
gregturn/gs-spring-boot-docker    latest              3e70f57df702        21 hours ago        841.4 MB

docker run -d -p 10.9.40.52:8089:8080 -p 127.0.0.1:8125:8125 springio/gs-spring-boot-docker
