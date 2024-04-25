package thread.state.monitor;

public class RunObjectThreads {

  public static void main(String[] args) {
    RunObjectThreads sample = new RunObjectThreads();
    sample.checkThreadState2();
  }

  public void checkThreadState2() {
    // 모니터를 밖에서 관리한다.
    Object monitor = new Object();
    StateThread thread = new StateThread(monitor);

    try {
      System.out.println("thread state=" + thread.getState());
      thread.start();
      System.out.println("thread state(after start)=" + thread.getState());
      Thread.sleep(100);
      System.out.println("thread state(after 0.1 sec)=" + thread.getState());

      synchronized (monitor) {
        monitor.notify();
      }

      Thread.sleep(100);

      System.out.println("thread state(after notify)="+ thread.getState());

      thread.join();

      System.out.println("thread state(after join)=" + thread.getState());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
