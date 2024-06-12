package database.distributelock.repository;

import database.distributelock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LockRepository extends JpaRepository<Stock, Long> {

  @Query(value = "select get_lock(:key, :timeoutSeconds)", nativeQuery = true)
  Long getLock(String key, int timeoutSeconds);

  @Query(value = "select release_lock(:key)", nativeQuery = true)
  void releaseLock(String key);
}
