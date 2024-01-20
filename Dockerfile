FROM openjdk:19-rc

ARG SCALA_VERSION=2.13.12
ARG SCALA_UNLOAD=https://github.com/scala/scala/archive/v${SCALA_VERSION}.tar.gz

ARG MAVEN_MAJOR=3
ARG MAVEN_VERSION=${MAVEN_MAJOR}.9.6
ARG MAVEN_UNLOAD=https://dlcdn.apache.org/maven/maven-${MAVEN_MAJOR}/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz

ARG HADOOP_VERSION=3.3.6
ARG HADOOP_UNLOAD=https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-${HADOOP_VERSION}/hadoop-${HADOOP_VERSION}.tar.gz

ARG SPARK_VERSION=3.4.2
ARG SPARK_UNLOAD=https://www.apache.org/dyn/closer.lua/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop3.tgz
