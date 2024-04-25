package thread.singleton;

public class SingletonObject2 {

  private static SingletonObject2 singleObject2 = null;

  private SingletonObject2() {

  }

  public synchronized static SingletonObject2 getInstance() throws InterruptedException {
    if (singleObject2 == null) {
      synchronized (SingletonObject2.class) {
        singleObject2 = new SingletonObject2();
      }
    }
    return singleObject2;
  }

}
