package org.example.httpclients.low;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpConfig {

  @Bean
  OkHttpClient okHttpClient() {
    return new OkHttpClient();
  }
}
