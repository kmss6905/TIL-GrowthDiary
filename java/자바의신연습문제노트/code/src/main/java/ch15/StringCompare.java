package ch15;

public class StringCompare {

  public static void main(String[] args) {
    StringCompare sample = new StringCompare();
//    sample.checkString();
//    sample.checkCompare();
//    sample.생성자로만든문자열과비교하기();
//    sample.contentEuqalsTest();
    String[] address = new String[]{
        "서울시 구로구 신도림동",
        "경기도 성남시 분ㄱ당구 정자동 개발공장",
        "서울시 구로구 개봉동"
    };
    sample.contentEuqalsTest(address);
    sample.checkIndexOf();
    sample.checkLastIndexOf();
    sample.checkCharAt();
    sample.checkSubString();
    sample.checkSplit();
    sample.checkTrim();
    sample.stringAppend();
    sample.stringBuffer();
  }

  public void checkString() {
    String text = "You must know String class.";
    System.out.println("text.length()=" + text.length());
  }

  public void checkCompare() {
    String text = "Check Value";
    String text2 = "Check Value";

    if (text == text2) {
      System.out.println("text == text2 result is same");
    } else {
      System.out.println("text == text2 result is different");
    }

    if (text.equals(text2)) {
      System.out.println("text.equals(text2) result is same");
    }
  }


  public void 생성자로만든문자열과비교하기(){
    String text = "Check Value";
    String text2 = new String("Check Value");
    if (text == text2) {
      System.out.println("text == text2 result is same");
    } else {
      System.out.println("text == text2 result is different");
    }

    if (text.equals(text2)) {
      System.out.println("text.equals(text2) result is same");
    }
    System.out.println("---------------");
  }

  public void contentEuqalsTest(String[] address) {
    int startCount=0, endCount=0;
    String startText = "서울시";
    String endText = "동";
    for (String addr : address) {
      if (addr.startsWith(startText)) {
        startCount++;
      }

      if (addr.endsWith(endText)) {
        endCount++;
      }
    }
    System.out.println("startCount = " + startCount);
    System.out.println("endCount = " + endCount);
    System.out.println("---------------");
  }

  public void checkIndexOf() {
    String text = "Java technology is both a programing language and a platform";
    System.out.println(text.indexOf('a'));
    System.out.println(text.indexOf("a "));
    System.out.println(text.indexOf('a', 20));
    System.out.println(text.indexOf("a ", 20));
    System.out.println(text.indexOf('z'));
    System.out.println("---------------");
  }

  public void checkLastIndexOf() {
    String text = "Java technology is both a programing language and a platform";
    System.out.println(text.lastIndexOf('a'));
    System.out.println(text.lastIndexOf("a "));
    System.out.println(text.lastIndexOf('a', 20));
    System.out.println(text.lastIndexOf("a ", 20));
    System.out.println(text.lastIndexOf('z'));
    System.out.println("---------------");
  }

  public void checkCharAt() {
    String text = "Java technology is both a programing language and a platform";
    System.out.println(text.charAt(0));
    System.out.println("---------------");
  }

  public void checkSubString() {
    String text = "Java technology";
    System.out.println("'Java technology'.substring(5) = " + text.substring(5) );
    String tech = text.substring(5, 9);
    System.out.println("'Java technology'.substring(5, 9); = " + tech);
    System.out.println("---------------");
  }

  public void checkSplit() {
    String text = "Java technology is both a programing language and a platform";
    String[] splitArray = text.split(" ");
    for (String s : splitArray) {
      System.out.println(s);
    }
    System.out.println("---------------");
  }

  public void checkTrim() {
    String[] strings = {
        " a", "b ", "        c", "d        ", "e     f", "    "
    };

    for (String string : strings) {
      System.out.println("[" + string + "]" );
      System.out.println("[" + string.trim() + "]" );
    }
    System.out.println("---------------");
  }

  public void stringAppend() {
    StringBuilder sb = new StringBuilder();
    sb.append("Hello");
    sb.append(" world");
    System.out.println(sb);
    System.out.println("---------------");
  }

  public void stringBuffer() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("Hello ");
    stringBuffer.append(" world");
    System.out.println(stringBuffer);
    System.out.println("---------------");
  }
}
