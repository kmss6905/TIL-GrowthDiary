package thread.singleton;

import java.util.ArrayList;
import java.util.List;

public class SingletonTest {

  public static void main(String[] args) {
    List<Thread> threadPool = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      threadPool.add(new Thread(() -> {
        try {
          System.out.println(Thread.currentThread().getName() + " : " + SingletonObject2.getInstance().hashCode());
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }));
    }

    for (int i = 0; i < threadPool.size(); i++) {
      threadPool.get(i).start();
    }
  }

}
