package ch16;

public class InnerSample {

  public static void main(String[] args) {
    InnerSample innerSample = new InnerSample();
    innerSample.makeInnerObject();
  }

  private void makeInnerObject() {
    OuterOfInner outerOfInner = new OuterOfInner();
    OuterOfInner.Inner inner = outerOfInner.new Inner();
    inner.setValue(5);
    System.out.println(inner.getValue());
  }
}
