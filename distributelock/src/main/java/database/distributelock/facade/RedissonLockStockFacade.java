package database.distributelock.facade;

import database.distributelock.service.StockService;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
public class RedissonLockStockFacade {

  private final RedissonClient redissonClient;
  private final StockService stockService;

  public RedissonLockStockFacade(RedissonClient redissonClient, StockService stockService) {
    this.redissonClient = redissonClient;
    this.stockService = stockService;
  }

  public void decrease(Long id, Long quantity) {
    RLock lock = redissonClient.getLock(id.toString());
    boolean lockAcquired = false;
    try {
      // 몇 초 동안 lock 흭득을 시도할 것인지
      // 몇 초 동안 점유할 것인지(코드 실행 로직 실행과 비교하여 정할 것)
      // tryLock 에서도 예외 발생 가능( thread 가 interrupted 될 경우 InterruptedException)
      lockAcquired = lock.tryLock(10, 1, TimeUnit.SECONDS);

      if (!lockAcquired) {
        System.out.println("lock 흭득 실패");
        return;
      }
      stockService.decrease(id, quantity);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      if (lockAcquired) {
        lock.unlock();
      }
    }
  }
}
