package points;

public class HelloPoint {

    public static void main(String[] args) {
      System.out.println("hello point");
      HelloPoint helloPoint = new HelloPoint();
      helloPoint.getPointColor();
    }

  public void getPointColor(){
    PointColor pointColor = new PointColor(10);
    System.out.println(pointColor.getColor());
  }

}