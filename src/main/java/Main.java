import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    static int numTasks = 4;
    static int numMessages = 3;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Integer>> list = pool.invokeAll(new MessageTasksImpl(numMessages).getTasks(numTasks));
        int result = 0;
        while (list.size() > 0) {
            Iterator<Future<Integer>> it = list.iterator();
            while (it.hasNext()) {
                Future<Integer> future = it.next();
                if (future.isDone()) {
                    result += future.get();
                    it.remove();
                }
            }
        }
        pool.shutdown();
        System.out.println("Выведено " + result + " сообщений");
    }
}
