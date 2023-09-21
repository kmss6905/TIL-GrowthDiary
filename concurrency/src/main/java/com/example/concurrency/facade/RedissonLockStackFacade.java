package com.example.concurrency.facade;

import com.example.concurrency.application.stock.StockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStackFacade {

  private final RedissonClient redissonClient;
  private final StockService stockService;


  public RedissonLockStackFacade(RedissonClient redissonClient, StockService stockService) {
    this.redissonClient = redissonClient;
    this.stockService = stockService;
  }

  public void decrease(Long id, Long quantity) {
    RLock lock = redissonClient.getLock(id.toString());
    try {
      boolean available = lock.tryLock(15, 1, TimeUnit.SECONDS);
      if (!available) {
        System.out.println("Lock 흭득 실패");
        return;
      }
      stockService.decrease(id, quantity);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }
}
