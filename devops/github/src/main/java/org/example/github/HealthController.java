package org.example.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @Value("${spring.application.name}")
  String appName;

  @Value("${app.version}")
  String appVersion;

  @GetMapping("/health")
  public String ok() {
    return appName + ": " + appVersion;
  }
}
