import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String result = "";
        final int n = 200;
        HighLoad highLoad = new HighLoad();
//        CrunchifyGenerateOOM oom = new CrunchifyGenerateOOM();

        SparkConf conf = new SparkConf().setMaster("local").setAppName("TestSparkParal");
        JavaSparkContext sc = new JavaSparkContext(conf);
        TaskFunction task = new TaskFunction(highLoad);
        JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(n / 4, n / 4, n / 4, n / 4));
        JavaRDD<String> strings = rdd.map(task);
        result = strings.reduce((a, b) -> a + b);
//        result = oom.execute(2000);
//        result = highLoad.execute(n);
        printMemoryStatistics();
        long endTime = System.currentTimeMillis();

        System.out.println(String.format("Iterations: %1$d, Result length: %2$d", n, result.length()));
        System.out.println("Execution time: " + new Date(endTime - startTime).getSeconds() + "sec");
    }

    private static void printMemoryStatistics() {
        // TODO Auto-generated method stub
        long heapSize = Runtime.getRuntime().totalMemory();

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();

        System.out.println("heapsize" + CrunchifyGenerateOOM.formatSize(heapSize));
        System.out.println("heapmaxsize" + CrunchifyGenerateOOM.formatSize(heapMaxSize));
        System.out.println("heapFreesize" + CrunchifyGenerateOOM.formatSize(heapFreeSize));
    }


    static class TaskFunction implements Function<Integer, String> {
        private Task task;

        public TaskFunction(Task task) {
            this.task = task;
        }

        public String call(Integer n) {
            return task.execute(n);
        }
    }

}