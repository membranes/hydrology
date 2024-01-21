# https://hub.docker.com/_/alpine/
FROM alpine:3.19


# Java
RUN apk update && apk add --no-cache openjdk-19-jdk-headless
ENV JAVA_HOME='$(readlink -f `which java` | sed "s:/bin/java::")'
ENV PATH=$JAVA_HOME/bin:$PATH


# Arguments
ARG SCALA_VERSION=2.13.12
ARG SCALA_ARCHIVE=v${SCALA_VERSION}
ARG SCALA_UNLOAD=https://github.com/scala/scala/archive/${SCALA_ARCHIVE}.tar.gz

ARG MAVEN_MAJOR=3
ARG MAVEN_VERSION=${MAVEN_MAJOR}.9.6
ARG MAVEN_ARCHIVE=apache-maven-${MAVEN_VERSION}-bin
ARG MAVEN_UNLOAD=https://dlcdn.apache.org/maven/maven-${MAVEN_MAJOR}/${MAVEN_VERSION}/binaries/${MAVEN_ARCHIVE}.tar.gz

ARG HADOOP_VERSION=3.3.6
ARG HADOOP_ARCHIVE=hadoop-${HADOOP_VERSION}
ARG HADOOP_UNLOAD=https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-${HADOOP_VERSION}/${HADOOP_ARCHIVE}.tar.gz

ARG SPARK_VERSION=3.4.2
ARG SPARK_ARCHIVE=spark-${SPARK_VERSION}-bin-hadoop3
ARG SPARK_UNLOAD=https://www.apache.org/dyn/closer.lua/spark/spark-${SPARK_VERSION}/${SPARK_ARCHIVE}.tgz


# Installing Scala, Maven, Hadoop, Spark
RUN apk cache clean && \
    wget -q ${SCALA_UNLOAD} && tar zxf ${SCALA_ARCHIVE}.tar.gz && mv ${SCALA_ARCHIVE} /opt/scala && rm ${SCALA_ARCHIVE}* && \
    wget -q ${MAVEN_UNLOAD} && tar zxf ${MAVEN_ARCHIVE}.tar.gz && mv ${MAVEN_ARCHIVE} /opt/maven && rm ${MAVEN_ARCHIVE}* && \
    wget -q ${HADOOP_UNLOAD} && tar zxf ${HADOOP_ARCHIVE}.tar.gz && mv ${HADOOP_ARCHIVE} /opt/hadoop && rm ${HADOOP_ARCHIVE}* && \
    wget -q ${SPARK_UNLOAD} && tar -zxvf ${SPARK_ARCHIVE}.tgz && mv ${SPARK_ARCHIVE} /opt/spark && rm ${SCALA_ARCHIVE}* && \
    cp /opt/spark/conf/log4j2.properties.template /opt/spark/conf/log4j2.properties


# Setting-up environment variables
ENV SCALA_HOME=/opt/scala
ENV MAVEN_HOME=/opt/maven
ENV HADOOP_HOME=/opt/hadoop
ENV SPARK_HOME=/opt/spark
ENV PATH=${SCALA_HOME}/bin:${MAVEN_HOME}/bin:${HADOOP_HOME}/bin:${SPARK_HOME}/bin:${SPARK_HOME}/sbin:$PATH


# Expose ports for monitoring.
# SparkContext web UI on 4040 -- only available for the duration of the application.
# Spark master’s web UI on 8080.
# Spark worker web UI on 8081.
EXPOSE 4040 8080 8081


# ENTRYPOINT
ENTRYPOINT ["bash"]