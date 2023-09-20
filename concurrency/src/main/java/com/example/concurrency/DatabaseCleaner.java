package com.example.concurrency;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DatabaseCleaner implements InitializingBean {

  @PersistenceContext
  private EntityManager entityManager;

  private final List<String> tableNames = new ArrayList<>();

  @Override
  public void afterPropertiesSet() {
    tableNames.add("coupon");
    tableNames.add("issued_coupon");
    tableNames.add("user");
  }

  @Transactional
  public void execute() {
    entityManager.flush();
    entityManager.createNativeQuery("SET foreign_key_checks = 0;").executeUpdate();
    tableNames.forEach(tableName -> executeQueryWithTable(tableName));
    entityManager.createNativeQuery("SET foreign_key_checks = 1;").executeUpdate();
  }

  private void executeQueryWithTable(String tableName) {
    entityManager.createNativeQuery("TRUNCATE TABLE " + tableName + ";").executeUpdate();
    entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1;").executeUpdate();
  }
}
