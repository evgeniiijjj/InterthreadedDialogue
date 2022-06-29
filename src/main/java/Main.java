import java.util.concurrent.*;

public class Main {
    static int numTasks = 4;
    static int numMessages = 3;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        String result = pool.invokeAny(new MessageTasksImpl(numMessages).getTasks(numTasks));
        pool.shutdown();
        System.out.println("Быстрее всех отработал " + result);
    }
}
