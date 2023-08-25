package com.example.filterinterceptor.controlller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "hello";
  }
}
