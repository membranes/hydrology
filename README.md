<br>

**hydro**

<br>

### Apache Spark Notes

Remember, for local exploration of apache spark scala executions via the interface you will need the commands

```bash
    spark-class.cmd org.apache.spark.deploy.master.Master
```

<br>

```bash
    spark-class.cmd org.apache.spark.deploy.worker.Worker spark://___.___._.__:7077
```

<br>

```bash
    mvn clean package
``` 

<br>

```shell
    spark-submit --class com.grey.HydroApp 
        --master spark://___.___._.__:7077 
        --total-executor-cores 4 
        target/hydro-#.#.##-jar-with-dependencies.jars
```

wherein `#.#.##` is the build number; depending on <span title='Project Object Model'>POM</span> settings, each run of `mvn clean package` creates a string 
of the form `hydro-#.#.##-jar-with-dependencies.jars`.

<br>
<br>

### Development Environment Notes

* [Repositories for POM (Project Object Model)](https://mvnrepository.com/repos)
* [Project Object Model](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)


<br>
<br>

<br>
<br>

<br>
<br>

<br>
<br>
