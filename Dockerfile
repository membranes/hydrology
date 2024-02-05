# The maven base image
FROM greyhypotheses/aiu:spark_scala_3.5.0


# Working Directory
WORKDIR /app
COPY ./target/*-jar-with-dependencies.jar /app/target

# ENTRYPOINT
ENTRYPOINT ["bash"]
