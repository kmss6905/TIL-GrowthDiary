package com.example.login.controller.cookie;

import com.example.login.controller.request.LoginRequestDto;
import com.example.login.user.User;
import com.example.login.user.UserStore;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {

  @PostMapping("/login")
  public String loginCookie(
          @RequestBody LoginRequestDto loginDto,
          HttpServletResponse response) {
    // login logic
    User user = findUser(loginDto.id());
    user.checkPwd(loginDto.getPassword());


    // set cookie
    response.addCookie(new Cookie("memberId", String.valueOf(user.getId())));
    return "ok";
  }

  private static User findUser(String id) {
    return UserStore.getInstance().findById(id);
  }

  @PostMapping("/logout")
  public String logout(HttpServletResponse response) {
    expireCookie(response, "memberId");
    return "ok";
  }

  private void expireCookie(HttpServletResponse response, String name) {
    Cookie cookie = new Cookie(name, null);
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  @GetMapping("/books")
  public List<String> getBooks(HttpServletRequest request) {
    String memberId = readCookie("memberId", request).get();
    System.out.println(memberId);
    return List.of("a", "b", "c");
  }

  public Optional<String> readCookie(String key, HttpServletRequest request) {
    return Arrays.stream(request.getCookies())
            .filter(c -> key.equals(c.getName()))
            .map(Cookie::getValue)
            .findAny();
  }
}

