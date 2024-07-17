package org.example.httpclients;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class HttpClientsApplication {

  interface ErApi{

    @GetExchange("/v6/latest")
    Map getLatest();
  }

  @Bean
  ErApi erApi() {
    WebClient client = WebClient.create("https://open.er-api.com");
    HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
        .builderFor(WebClientAdapter.create(client))
        .build();
    return httpServiceProxyFactory.createClient(ErApi.class);
  }

  @Bean
  ApplicationRunner init(ErApi api) {
    return args -> {
      // 1.RestTemplate
      RestTemplate rt = new RestTemplate();
      Map<String, Map<String, Double>> res = rt.getForObject("https://open.er-api.com/v6/latest", Map.class);
      System.out.println(res.get("rates").get("KRW"));

      // 2.WebClient
      // 현재 스레드 block 시킨다.
      // webclient 뒤에서 별도의 스레드로 (netty client 의 워크 스레드에서 돌고) 돌고 그것의 응답을 받을 때 까지 현재 스레드를 block 시키는 구조
      WebClient client = WebClient.create("https://open.er-api.com");
      Map<String, Map<String, Double>> res2 = client.get().uri("/v6/latest").retrieve().bodyToMono(Map.class).block();
      System.out.println(res2.get("rates").get("KRW"));

      // 3.HttpInterface
      // 매번 사용할 때마다 프록시 팩토리 만드는 과정 -> ErApi 같은 인터페이스를 구현한 클래스를 bean 으로 등록하고 주입받아서 사용한다.
      Map<String, Map<String, Double>> res3 = api.getLatest();
      System.out.println(res3.get("rates").get("KRW"));
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(HttpClientsApplication.class, args);
  }

}
