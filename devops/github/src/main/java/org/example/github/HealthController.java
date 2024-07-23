package org.example.github;

import org.springframework.web.bind.annotation.GetMapping;

public class HealthController {

  @GetMapping("/health")
  public String ok() {
    return "ok";
  }
}
