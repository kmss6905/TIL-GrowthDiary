public class IngeritanceCasting{

  public static void main(String[] args) {
    IngeritanceCasting ingeritanceCasting = new IngeritanceCasting();
    ingeritanceCasting.objectCastArray();
  }

  public void objectCastArray() {
    Parent[] parrentArray = new Parent[3];
    parrentArray[0] = new Child();
    parrentArray[1] = new Parent();
    parrentArray[2] = new Child();
    objectTypeCheck(parrentArray);
  }

  public void objectTypeCheck(Parent[] parrentArray) {
    System.out.println(parrentArray[0] instanceof Parent);

    for (Parent tempParent : parrentArray) {
      if (tempParent instanceof Child) {
        System.out.println("ChildCasting");
        Child child = (Child) tempParent;
        child.printName();
      } else if (tempParent instanceof Parent) {
        System.out.println("ParentCasting");
      }
    }
  }
}