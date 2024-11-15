package database.distributelock.facade;

import database.distributelock.repository.LockRepository;
import database.distributelock.service.StockService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NamedLockStockFacade {

  private final LockRepository lockRepository;
  private final StockService stockService;

  public NamedLockStockFacade(LockRepository lockRepository, StockService stockService) {
    this.lockRepository = lockRepository;
    this.stockService = stockService;
  }

  @Transactional
  public void decrease(Long id, Long quantity) {
    Long lock = null;
    try{
      lock = lockRepository.getLock(id.toString(), 1);
      if (lock == null || lock == 0) {
        System.out.println("락을 획득하지 못함.");
        return;
      }
      Thread.sleep(100);
      stockService.decrease(id, quantity);
    }
    catch (Exception e) {
      // 예외 로그 출력 및 처리
      System.err.println("예외 발생: " + e.getMessage());
      e.printStackTrace();
    } finally {
      // 락 해제 보장
      if (lock != null && lock != 0) {
        try {
          lockRepository.releaseLock(id.toString());
        } catch (Exception e) {
          // 락 해제 중 발생한 예외 처리
          System.err.println("락 해제 중 예외 발생: " + e.getMessage());
          e.printStackTrace();
        }
      }
    }
  }
}
