package ch16;

public class OuterOfStatic {

  public static void main(String[] args) {

  }

  static class StaticNested {
    private int value=0;

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

  }


}
