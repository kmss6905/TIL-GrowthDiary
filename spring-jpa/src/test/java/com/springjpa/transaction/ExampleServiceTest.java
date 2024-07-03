package com.springjpa.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExampleServiceTest {

  @Autowired
  private ExampleService exampleService;

  @Test
  void doService() {
    exampleService.doService();
  }
}