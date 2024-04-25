package three;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

  public static int count = 0;
  public static Lock lock = new ReentrantLock();

  public static void main(String[] args) throws InterruptedException {
    // 쓰레드는 끝난 후에 join 이라고 조욜해줘야한다.
    // 정식적은 바업은 main 스레드가 있지만.
    // main 으로 분기된것들은. 메인 스레드가 끝나기전에 원래로 복구해야한다.
    // 처음에는 6만했다가 왜 3만?
    // 처음에 동작할 때는 동작이 느리지만 나중에는 빠르다.
    // JVM 첫 호출은 컴파일이 안되서 느리지만, 어느정도 그 클래스가 사용되면 아예 컴파일 시켜서 메모리에 올린다.
    List<Thread> pool = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Thread t = new MyThread1();
      pool.add(t);
      t.start();
    }

    for (Thread t : pool) {
      t.join();
    }

    System.out.println("counter : " + count);
  }
}

class MyThread1 extends Thread {

  @Override
  public void run() {
    // synchronized 빠르다
    // OS 상에서는 모니터 기법이다.(가장 쉬운거는 Mutax 이다)
    // 이 블럭 안에 들어오면 JVM 이 안에 충돌이 발생하는 지 안하는 지 감시 함
    // 락도 있음. 대신 싱크로나이즈모다 더 많다!
//    synchronized (Main.class) {
//      for (int i = 0; i < 10000; i++) {
//        Main.count++;
//      }
//    }

      for (int i = 0; i < 10000; i++) {
        Main.lock.lock();

        try {

          Main.count++;
        }finally {

          Main.lock.unlock();
        }
      }
  }
}

// 이게 좀 더 선호되는 방법
class MyThread2 implements Runnable {
  @Override
  public void run() {

  }
}
