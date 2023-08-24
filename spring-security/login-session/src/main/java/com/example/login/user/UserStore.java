package com.example.login.user;

import java.util.ArrayList;
import java.util.List;

public class UserStore {

  public static final UserStore instance = new UserStore();
  private final List<User> users = new ArrayList<>();

  private UserStore() {
    users.add(User.builder()
            .id("user1")
            .pwd("user1")
            .build());
  }

  public static UserStore getInstance() {
    return instance;
  }

  private void init() {
  }

  public void save(User user) {
    this.users.add(user);
  }

  public User findById(String id) {
    return this.users.stream().filter(it -> it.getId().equals(id)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("not found user id :" + id));
  }


}
