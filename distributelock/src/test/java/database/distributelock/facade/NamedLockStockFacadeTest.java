package database.distributelock.facade;

import static org.junit.jupiter.api.Assertions.*;

import database.distributelock.domain.Stock;
import database.distributelock.repository.StockRepository;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NamedLockStockFacadeTest {

  @Autowired
  private NamedLockStockFacade lockStockFacade;

  @Autowired
  private StockRepository stockRepository;

  @BeforeEach
  public void insert() {
    Stock stock = new Stock(1L, 20L);
    stockRepository.saveAndFlush(stock);
  }

  @AfterEach
  public void delete() {
    stockRepository.deleteAll();
  }

  @Test
  public void 동시에_100개의요청() throws InterruptedException {
    int threadCount = 20;
    ExecutorService executorService = Executors.newFixedThreadPool(32);
    CountDownLatch latch = new CountDownLatch(threadCount);

    for (int i = 0; i < threadCount; i++) {
      executorService.submit(() -> {
        try {
          lockStockFacade.decrease(1L, 1L);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await();

    Stock stock = stockRepository.findById(1L).orElseThrow();

    // 100 - (100 * 1) = 0
    assertEquals(0, stock.getQuantity());
  }
}