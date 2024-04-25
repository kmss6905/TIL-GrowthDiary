package thread.sync;

public class RunSync {

  public static void main(String[] args) {
    RunSync runSync = new RunSync();
    runSync.runCommonCalculate();
  }

  public void runCommonCalculate() {
    CommonCalculate calc = new CommonCalculate();
    ModifyAmountThread thread1 = new ModifyAmountThread(calc, true);
    ModifyAmountThread thread2 = new ModifyAmountThread(calc, true);

    long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
    thread1.start();
    thread2.start();
    try {
      thread1.join();
      thread2.join();


      System.out.println("Final value is " + calc.getAmount());
      long afterTime = System.currentTimeMillis();      // 코드 실행 후에 시간 받아오기
      long secDiffTime = (afterTime - beforeTime)/1000; //두 시간에 차 계산
      System.out.println(secDiffTime);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
