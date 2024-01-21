# https://hub.docker.com/_/alpine/
FROM alpine:3.19

RUN apk update && apk add --no-cache openjdk-19-jdk-headless maven

ENV JAVA_HOME='$(readlink -f `which java` | sed "s:/bin/java::")'
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH


