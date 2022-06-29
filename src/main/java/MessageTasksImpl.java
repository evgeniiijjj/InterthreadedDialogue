import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MessageTasksImpl implements MessageTasks<Integer> {
    private final int numMessages;

    public MessageTasksImpl(int numMessages) {
        this.numMessages = numMessages;
    }

    @Override
    public List<Callable<Integer>> getTasks(int numTasks) {
        List<Callable<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numTasks; i++) {
            list.add(() -> {
                String name = Thread.currentThread().getName();
                int j = 0;
                for ( ; j < numMessages; j++) {
                    System.out.printf("Я %s, всем привет!\n", name);
                    Thread.sleep(2000);
                }
                return j;
            });
        }
        return list;
    }
}
