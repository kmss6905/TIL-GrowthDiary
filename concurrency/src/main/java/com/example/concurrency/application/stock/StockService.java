package com.example.concurrency.application.stock;

import com.example.concurrency.domain.stock.Stock;
import com.example.concurrency.domain.stock.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
  private final StockRepository stockRepository;

  public StockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  // 부모의 트랜잭션과 별도로 실행되어야 한다.
   @Transactional(propagation = Propagation.REQUIRES_NEW)
  public synchronized void decrease(Long id, Long quantity) {
    Stock stock = stockRepository.findById(id).orElseThrow();
    stock.decrease(quantity);
    stockRepository.saveAndFlush(stock);
  }
}
