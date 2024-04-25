package thread.state;

public class ThreadStateTest {

  public static void main(String[] args) {
    SleepThread sleepThread = new SleepThread(10000);
    sleepThread.start();
  }

  public void checkThreadState() {

  }
}

class SleepThread extends Thread{
  long sleepTime;

  public SleepThread(long sleepTime) {
    this.sleepTime = sleepTime;
  }

  @Override
  public void run() {
    try {
      System.out.println("Sleeping " + getName());
      Thread.sleep(sleepTime);
      System.out.println("Sleeping " + getName());
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
  }
}
