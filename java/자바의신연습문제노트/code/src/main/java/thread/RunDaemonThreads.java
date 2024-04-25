package thread;

public class RunDaemonThreads {

  public static void main(String[] args) {
    RunDaemonThreads runDaemonThreads = new RunDaemonThreads();
    runDaemonThreads.runCommonThread();
  }

  public void runCommonThread() {
    DaemonThread thread = new DaemonThread();
    thread.setDaemon(true);
    thread.start();
  }

  private void checkThreadProperty() {
    ThreadSample threadSample1 = new ThreadSample();
    ThreadSample threadSample2 = new ThreadSample();
    ThreadSample daemonThread = new ThreadSample();

    System.out.println("thread1 id = " + threadSample1.getId());
    System.out.println("thread2 id = " + threadSample2.getId());

    System.out.println("thread1 name= " + threadSample1.getName());
    System.out.println("thread2 name= " + threadSample2.getName());

    // 기본값은 5
    System.out.println("thread1 priority= " +  threadSample1.getPriority());

    daemonThread.setDaemon(true);
    System.out.println("thread 1 is Daemon=" + threadSample1.isDaemon());
    System.out.println("daemonThread isDaemon=" + daemonThread.isDaemon());
  }
}
