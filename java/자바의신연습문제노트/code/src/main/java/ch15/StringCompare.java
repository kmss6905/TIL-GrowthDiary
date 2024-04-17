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
  }


}
