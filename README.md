<br>

**hydrology**

<br>

### Apache Spark Notes

Remember, for local exploration of apache spark scala executions via the interface you will need the commands

```bash
    spark-class.cmd org.apache.spark.deploy.master.Master
    spark-class.cmd org.apache.spark.deploy.worker.Worker spark://___.___._.__:7077
    mvn clean package
```


```shell
    spark-submit --class com.grey.HydroApp 
        --master spark://___.___._.__:7077 
        --total-executor-cores 4 
        target/hydro-#.#.##-jar-with-dependencies.jars
```

wherein `#.#.##` is the build number; depending on <span title='Project Object Model'>POM</span> settings, each run of `mvn clean package` creates a string of the form `hydro-#.#.##-jar-with-dependencies.jars`.

<br>

### Development Environment Notes

POM (Project Object Model)
* [Repositories for POM (Project Object Model)](https://mvnrepository.com/repos)
* [Project Object Model](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)

[OpenJDK](https://hub.docker.com/_/openjdk)
* [19](https://hub.docker.com/layers/library/openjdk/19-rc/images/sha256-973fe414a4e1f3e41e291b068183684a88827dd2cb5f78214da26632d5218702?context=explore)


<br>
<br>

<br>
<br>

<br>
<br>

<br>
<br>
