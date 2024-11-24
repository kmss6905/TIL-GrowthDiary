package parallelism.springparallelism;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parallelism.springparallelism.safe.OneValueCache;

@RestController
public class SampleController {

  private volatile OneValueCache cache = new OneValueCache(BigInteger.ONE, new BigInteger[]{BigInteger.ONE});

  @GetMapping("/factor/{id}")
  public OneValueCache sample(long id) {
    BigInteger i = BigInteger.valueOf(id);
    BigInteger[] factors = cache.getLastFactors(i);
    factors = factor(i);
    cache = new OneValueCache(i, factors);
    return cache;
  }

  private BigInteger[] factor(BigInteger i) {
    List<BigInteger> factors = new ArrayList<>();
    BigInteger divisor = BigInteger.TWO;

    while (i.compareTo(BigInteger.ONE) > 0) {
      while (i.mod(divisor).equals(BigInteger.ZERO)) {
        factors.add(divisor);
        i = i.divide(divisor);
      }
      divisor = divisor.add(BigInteger.ONE);
    }

    return factors.toArray(new BigInteger[0]);
  }
}
