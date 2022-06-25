import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Callable<Integer>> taskList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            taskList.add(() -> {
                String name = Thread.currentThread().getName();
                int j = 0;
                for ( ;j < 3; j++) {
                    System.out.println("Я " + name + " всем привет!");
                    Thread.sleep(2000);
                }
                return j;
            });
        }
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> list = pool.invokeAll(taskList);
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
