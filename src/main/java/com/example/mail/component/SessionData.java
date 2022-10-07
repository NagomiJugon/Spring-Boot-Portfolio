package com.example.mail.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.mail.entity.AppUser;

@Component
public class SessionData implements Serializable {

  private static final long serialVersionUID = 808198990406616280L;

  private Map<String, AppUser> users;

  public SessionData() {
    this.users = new HashMap<String, AppUser>();
  }

  public Map<String, AppUser> getUsers() {
    return users;
  }

  public void setUsers(String sessionId, AppUser appUser) {
    this.users.put(sessionId, appUser);
  }

  public void signOut(String sessionId) {
    this.users.remove(sessionId);
  }

}
