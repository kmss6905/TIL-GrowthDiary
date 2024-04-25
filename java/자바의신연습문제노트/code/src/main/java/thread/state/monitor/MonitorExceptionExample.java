package thread.state.monitor;

import java.util.ArrayList;
import java.util.List;

public class MonitorExceptionExample {

  public static void main(String[] args) {
      ThreadA runA = new ThreadA();
      ThreadB runB = new ThreadB(runA);

      new Thread(runA).start();
      new Thread(runB).start();
  }
}

class ThreadA implements Runnable {

  List<String> recvMessages;

  public ThreadA() {
    recvMessages = new ArrayList<>();
  }

  public synchronized void run() {
    while(true) {
      if( recvMessages.isEmpty()) {
        try {
          recvMessages.wait();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      System.out.println("Receive Message: " + (String)recvMessages.remove(0));
    }
  }

  public void processMessage(String message) {
    this.recvMessages.add(message);
    this.recvMessages.notify();
  }
}
