package three;

public class SingleTone {

  // 2.
  public static final SingleTone singleTone = new SingleTone();

  // 1.
  private SingleTone() {

  }

  public static SingleTone getInstance() {
    return singleTone;
  }

}
