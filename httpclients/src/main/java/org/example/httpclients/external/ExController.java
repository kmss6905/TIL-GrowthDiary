package org.example.httpclients.external;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExController {

  @GetMapping("/api/ok")
  public ResponseEntity<String> getBook() {
    return ResponseEntity.ok("ok");
  }
}
