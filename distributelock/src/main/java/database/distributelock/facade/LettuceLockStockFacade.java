package database.distributelock.facade;

import database.distributelock.repository.RedisLockRepository;
import database.distributelock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

  private RedisLockRepository redisLockRepository;
  private StockService stockService;

  public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
    this.redisLockRepository = redisLockRepository;
    this.stockService = stockService;
  }

  public void decrease(Long key, Long quantity) {
    while (!redisLockRepository.lock(key)) {
      try {
        Thread.sleep(100); // wait
      } catch (InterruptedException e) {
        throw new RuntimeException();
      }
    }

    try {
      stockService.decrease(key, quantity);
    } finally {
      redisLockRepository.unlock(key);
    }

  }
}
