package c.javapackage;
import c.javapackage.sub.Sub;

import static c.javapackage.sub.SubStatic.CLASS_NAME;
import static c.javapackage.sub.SubStatic.subStaticMethod;

public class Package {
  public static final String CLASS_NAME = "Hello World";

  public static void main(String[] args) {
    System.out.println("package class.");
    Sub sub = new Sub();
    sub.subClassMethod();

    subStaticMethod();
    System.out.println(CLASS_NAME);
  }

  private static void subStaticMethod() {
    System.out.println("Package's subStaticMethod");
  }
}