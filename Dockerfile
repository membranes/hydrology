FROM openjdk:19-rc

ARG SCALA_VERSION=2.13.12
ARG SCALA_ARCHIVE=v${SCALA_VERSION}.tar.gz
ARG SCALA_UNLOAD=https://github.com/scala/scala/archive/${SCALA_ARCHIVE}

ARG MAVEN_MAJOR=3
ARG MAVEN_VERSION=${MAVEN_MAJOR}.9.6
ARG MAVEN_ARCHIVE=apache-maven-${MAVEN_VERSION}-bin.tar.gz
ARG MAVEN_UNLOAD=https://dlcdn.apache.org/maven/maven-${MAVEN_MAJOR}/${MAVEN_VERSION}/binaries/${MAVEN_ARCHIVE}

ARG HADOOP_VERSION=3.3.6
ARG HADOOP_ARCHIVE=hadoop-${HADOOP_VERSION}.tar.gz
ARG HADOOP_UNLOAD=https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-${HADOOP_VERSION}/${HADOOP_ARCHIVE}

ARG SPARK_VERSION=3.4.2
ARG SPARK_ARCHIVE=spark-${SPARK_VERSION}-bin-hadoop3.tgz
ARG SPARK_UNLOAD=https://www.apache.org/dyn/closer.lua/spark/spark-${SPARK_VERSION}/${SPARK_ARCHIVE}

RUN apt -y update && \
    apt clean && \
    wget -q ${SCALA_UNLOAD} && \
    tar zxf ${SCALA_ARCHIVE} && \
    mv ${SCALA_ARCHIVE} /opt/scala && \
    rm ${SCALA_ARCHIVE} && \
    wget -q ${MAVEN_UNLOAD} && \
    tar zxf ${MAVEN_ARCHIVE} && \
    mv ${MAVEN_ARCHIVE} /opt/maven && \
    rm ${MAVEN_ARCHIVE} && \
    wget -q ${HADOOP_UNLOAD} && \
    tar zxf ${HADOOP_ARCHIVE}
