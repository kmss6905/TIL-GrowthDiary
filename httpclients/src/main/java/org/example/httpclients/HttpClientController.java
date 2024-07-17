package org.example.httpclients;

import org.example.httpclients.httpinterface.RepositoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@RestController
public class HttpClientController {
  
  private final RestTemplate restTemplate;

  public HttpClientController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/")
  public void test() {
    RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
    RepositoryService service = factory.createClient(RepositoryService.class);
    service.getOk();
  }
}
