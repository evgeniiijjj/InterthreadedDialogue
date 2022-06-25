public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("main group");
        for (int i = 0; i < 4; i++) {
            new MyThread(group, "thread" + i).start();
        }
        Thread.sleep(15000);
        group.interrupt();
    }
}
