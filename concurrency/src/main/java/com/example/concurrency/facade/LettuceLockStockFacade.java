package com.example.concurrency.facade;

import com.example.concurrency.application.stock.StockService;
import com.example.concurrency.repository.RedisRockRepository;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

  private final RedisRockRepository redisRockRepository;
  private final StockService stockService;

  public LettuceLockStockFacade(RedisRockRepository redisRockRepository, StockService stockService) {
    this.redisRockRepository = redisRockRepository;
    this.stockService = stockService;
  }

  public void decrease(Long id, Long quantity) throws InterruptedException {
    while (!redisRockRepository.lock(id)) { // lock 흭득을 재시도
      Thread.sleep(100);
    }
    try{
      stockService.decrease(id, quantity);
    }finally {
      redisRockRepository.unlock(id);
    }
  }

}
