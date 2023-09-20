package com.example.concurrency.application.stock;

import com.example.concurrency.domain.stock.Stock;
import com.example.concurrency.domain.stock.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptimisticLockStockService {
  private final StockRepository stockRepository;

  public OptimisticLockStockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @Transactional
  public void decrease(Long id, Long quantity) {
    Stock stock = stockRepository.findByWithOptimisticLock(id);

    // 실패 시 재시도 해야함

    stock.decrease(quantity);
    stockRepository.saveAndFlush(stock);
  }
}
