# The maven base image
FROM greyhypotheses/aiu:spark_scala_3.5.0


# Working Directory
WORKDIR /app


# ENTRYPOINT
ENTRYPOINT ["bash"]
