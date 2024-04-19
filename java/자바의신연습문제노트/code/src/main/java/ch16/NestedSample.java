package ch16;

public class NestedSample {

  public static void main(String[] args) {
    NestedSample nestedSample = new NestedSample();
    nestedSample.makeStaticNestedObject();
  }

  private void makeStaticNestedObject() {
    OuterOfStatic.StaticNested staticNested = new OuterOfStatic.StaticNested();
    staticNested.setValue(3);
    System.out.println(staticNested.getValue());
  }
}
