package thread.state;

public class RunSupportThreads {

  public static void main(String[] args) {
    Thread.dumpStack();

    RunSupportThreads sample = new RunSupportThreads();
    sample.checkThreadState();
//    sample.checkJoin();
  }

  private void checkThreadState() {
    SleepThread thread = new SleepThread(2000);

    try {
      // Thread 가 시작하지 않았기 때문에 NEW
      System.out.println("thread state = " + thread.getState());
      thread.start();
      // Thread 가 시작한 상태이기 때문에 Runnable
      System.out.println("thread state(after start)= "+ thread.getState());
      System.out.println("thread is alive =" + thread.isAlive());
      // 주의! main 스레드가 잠시 멈추는 것임.
      Thread.sleep(1000);
      // Thread 가 Sleep 상태이기 때문에 TIME_WAITING
      System.out.println("thread state(after 1sec)= "+ thread.getState());

      // 수행중인 쓰레드가 중지할 때 까지 대기한다.
      thread.join();

      // 수행중인 쓰레드에 중지 요청을 한다.
      thread.interrupt();

      // Thread 가 종료되기를 thread.join() 메서드에서 기다린 후의 상태이므로 TERMINATED
      System.out.println("thread state(after 1 sec)=" + thread.getState());
      System.out.println("thread is alive =" + thread.isAlive());
      System.out.println("thread is Interrupted()= " + thread.isInterrupted());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void checkJoin() {
    SleepThread thread = new SleepThread(2000);
    try {
      thread.start();
      thread.join(500);
      thread.interrupt();
      System.out.println("thread state (after join)=" + thread.getState());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
