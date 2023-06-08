<br>

**hydro**

<br>

### Notes

Remember, for local exploration of apache spark scala executions via the interface you will need the command akin to

```shell
spark-class.cmd org.apache.spark.deploy.master.Master
spark-class.cmd org.apache.spark.deploy.worker.Worker spark://___.___._.__:7077

spark-submit --class com.grey.HydroApp 
	--master spark://___.___._.__:7077 
	--total-executor-cores 4 
	target/hydro-#.#.##-jar-with-dependencies.jars
```

wherein `#.#.##` is the build number.  The compile command is

```shell
mvn clean package
``` 

<br>
<br>

### Development Environment

* [Repositories for POM (Project Object Model)](https://mvnrepository.com/repos)


<br>
<br>

<br>
<br>

<br>
<br>

<br>
<br>
