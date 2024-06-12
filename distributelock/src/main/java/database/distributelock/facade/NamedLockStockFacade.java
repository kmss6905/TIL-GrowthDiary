package database.distributelock.facade;

import database.distributelock.repository.LockRepository;
import database.distributelock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class NamedLockStockFacade {

  private final LockRepository lockRepository;
  private final StockService stockService;

  public NamedLockStockFacade(LockRepository lockRepository, StockService stockService) {
    this.lockRepository = lockRepository;
    this.stockService = stockService;
  }

  public void decrease(Long id, Long quantity) {
    try{
      Long available = lockRepository.getLock(id.toString(), 3000);
      if (available == 0) { // 락을 흭득하지 못함.
        System.out.println("락을 획득하지 못함.");
        return;
      }
      stockService.decrease(id, quantity);
    } finally {
      lockRepository.releaseLock(id.toString());
    }
  }
}
