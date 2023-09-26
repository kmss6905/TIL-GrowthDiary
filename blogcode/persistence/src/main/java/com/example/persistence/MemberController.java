package com.example.persistence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  @GetMapping("/members/{id}")
  public MemberDto update(
          @PathVariable Long id,
          @RequestParam String name
  ) {
    return memberService.update(id, name);
  }
}
