package database.springfilterinterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

  // required authentication user
  public void decreaseStock() {
    // do logic
    log.info(" 재고감소 로직");
  }
}
