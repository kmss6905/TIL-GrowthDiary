package thread.state.monitor;

public class RunObjectThreads {

  public static void main(String[] args) {
    RunObjectThreads sample = new RunObjectThreads();
//    sample.checkThreadState2();
    sample.checkThreadState3();
  }

  public void checkThreadState3() {
    Object monitor = new Object();
    StateThread thread = new StateThread(monitor);
    StateThread thread2 = new StateThread(monitor);

    try {
      System.out.println("thread state=" + thread.getState());
      thread.start();
      thread2.start();
      System.out.println("thread state(after start)=" + thread.getState());
      Thread.sleep(100);
      System.out.println("thread state(after 0.1 sec)=" + thread.getState());

      synchronized (monitor) {
        // 모니터를 흭득하고 있는 모든 스레드를 깨운다.
        monitor.notifyAll();
      }

      Thread.sleep(100);

      System.out.println("thread state(after notify)="+ thread.getState());

      thread.join();

      System.out.println("thread state(after join)=" + thread.getState());
      System.out.println("thread2 state(after join)=" + thread2.getState());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
