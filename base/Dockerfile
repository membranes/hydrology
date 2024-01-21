
FROM maven:3.9.6-eclipse-temurin-21-alpine
ENV HOME=/usr/app
WORKDIR $HOME
RUN mkdir -p $HOME
COPY . $HOME
RUN --mount=type=cache,target=/root/.m2 mvn -f $HOME/pom.xml clean package


FROM openjdk:19-rc

ARG SCALA_VERSION=2.13.12
ARG SCALA_ARCHIVE=v${SCALA_VERSION}
ARG SCALA_UNLOAD=https://github.com/scala/scala/archive/${SCALA_ARCHIVE}.tar.gz

ARG HADOOP_VERSION=3.3.6
ARG HADOOP_ARCHIVE=hadoop-${HADOOP_VERSION}
ARG HADOOP_UNLOAD=https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-${HADOOP_VERSION}/${HADOOP_ARCHIVE}.tar.gz

ARG SPARK_VERSION=3.4.2
ARG SPARK_ARCHIVE=spark-${SPARK_VERSION}-bin-hadoop3
ARG SPARK_UNLOAD=https://www.apache.org/dyn/closer.lua/spark/spark-${SPARK_VERSION}/${SPARK_ARCHIVE}.tgz

RUN apt -y update && \
    apt clean && \
    wget -q ${SCALA_UNLOAD} && tar zxf ${SCALA_ARCHIVE}.tar.gz && mv ${SCALA_ARCHIVE} /opt/scala && rm ${SCALA_ARCHIVE}* && \
    wget -q ${HADOOP_UNLOAD} && tar zxf ${HADOOP_ARCHIVE}.tar.gz && mv ${HADOOP_ARCHIVE} /opt/hadoop && rm ${HADOOP_ARCHIVE}* && \
    wget -q ${SPARK_UNLOAD} && tar -zxvf ${SPARK_ARCHIVE}.tgz && mv ${SPARK_ARCHIVE} /opt/spark && rm ${SCALA_ARCHIVE}*


ENV SCALA_HOME=/opt/scala
ENV HADOOP_HOME=/opt/hadoop
ENV SPARK_HOME=/opt/spark
ENV PATH=$SCALA_HOME/bin:$HADOOP_HOME/bin:$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH