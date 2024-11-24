package parallelism.springparallelism.safe;

import java.math.BigInteger;
import java.util.Arrays;
import parallelism.springparallelism.annotation.Immutable;

@Immutable
public class OneValueCache {
  private final BigInteger lasNumber;
  private final BigInteger[] lastFactors;

  public OneValueCache(BigInteger lasNumber, BigInteger[] lastFactors) {
    this.lasNumber = lasNumber;
    this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
  }

  public BigInteger[] getLastFactors(BigInteger i) {
    if (lasNumber == null || !lasNumber.equals(i))
      return null;
    else
      return Arrays.copyOf(lastFactors, lastFactors.length);
  }
}
