package thread.state.monitor;

public class StateThread extends Thread{

  private Object monitor;

  public StateThread(Object monitor) {
    this.monitor = monitor;
  }

  @Override
  public void run() {
    try {
        for (int i = 0; i < 10000; i++) {
          String a = "A";
        }

        // TIME_WAITING
        synchronized (monitor) {
          monitor.wait();
        }

        System.out.println(getName() + " is notified.");
        Thread.sleep(1000);
    } catch (InterruptedException ie) {

    }
  }
}
