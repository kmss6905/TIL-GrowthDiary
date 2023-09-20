package com.example.concurrency;

import com.example.concurrency.dto.CouponIssueDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponConcurrencyTest {

  @Autowired
  private MockMvc mvc;
  private final ObjectMapper objectMapper = new ObjectMapper();


  @Test
  void test() throws Exception {
    CouponIssueDto couponIssueDto = new CouponIssueDto(2L, 1L);
    String body = objectMapper.writeValueAsString(couponIssueDto);
    final int maxCore = Runtime.getRuntime().availableProcessors();



//    ExecutorService executor = Executors.newFixedThreadPool(4);
//    executor.submit(() -> {
//    });
    mvc.perform(post("/issue")
                    .content(body)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }
}
