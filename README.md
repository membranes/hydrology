<br>

**hydro**

<br>

### Notes

Remember, for local exploration of apache spark scala executions via the interface you will need the command akin to

```Shell
spark-class.cmd org.apache.spark.deploy.master.Master
spark-class.cmd org.apache.spark.deploy.worker.Worker spark://___.___._.__:7077

spark-submit --class com.grey.HydroApp 
	--master spark://___.___._.__:7077 --total-executor-cores 4 target/hydro-#.#.##-jar-with-dependencies.jars
```

wherein `#.#.##` is the buld number.  The compile command is

```Shell
mvn clean package
``` 


<br>
<br>

<br>
<br>

<br>
<br>

<br>
<br>
