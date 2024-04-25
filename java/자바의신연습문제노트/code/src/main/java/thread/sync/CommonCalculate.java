package thread.sync;

import java.util.concurrent.ConcurrentMap;

public class CommonCalculate {
  private int amount;

  public CommonCalculate() {
    amount = 0;
  }

  public void plus(int value) {
    synchronized (CommonCalculate.class) {
      amount += value;
    }
  }

  public void minus(int value) {
    synchronized (CommonCalculate.class) {
      amount -= value;
    }
  }

  public int getAmount() {
    return amount;
  }

}
