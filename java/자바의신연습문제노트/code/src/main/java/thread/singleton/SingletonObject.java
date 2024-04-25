package thread.singleton;

// 싱글톤 패턴이란?
// 객체 인스턴스가 클래스에 딱 하나만 생성되도록 한다.
public class SingletonObject {

  private static final SingletonObject singletonObject = new SingletonObject();

  private SingletonObject() {

  }

  public static SingletonObject getInstance() {
    return singletonObject;
  }
}
