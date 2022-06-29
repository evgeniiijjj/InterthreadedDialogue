import java.util.List;
import java.util.concurrent.Callable;

public interface MessageTasks<V> {
    List<Callable<V>> getTasks(int numTasks);
}
