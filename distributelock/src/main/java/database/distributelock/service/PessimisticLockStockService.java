package database.distributelock.service;

import database.distributelock.domain.Stock;
import database.distributelock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

  private final StockRepository stockRepository;

  public PessimisticLockStockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @Transactional
  public void decrease(Long id, Long quantity) {
    // 실제 db 에 락을 걸기 때문에 성능저하가 있을 수 있다.
    // for update 쿼리에 의해 설정된 모든 잠금은 트랜잭션이 커밋되거나 롤백될 때 해제된다.
    Stock stock = stockRepository.findByWithPessimisticLock(id);
    stock.decrease(quantity);
  }
}
