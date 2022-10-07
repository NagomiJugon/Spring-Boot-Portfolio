package com.example.mail.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.mail.entity.AppUser;

@Component
public class SessionData implements Serializable {

  private static final long serialVersionUID = 808198990406616280L;

  private int id;
  private String userName;
  private Map<String, AppUser> users;

  public SessionData() {
    this.users = new HashMap<String, AppUser>();
  }

  /**
   * @return int return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  public void clear() {
    this.id = 0;
    this.userName = "";
  }

  /**
   * @return String return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
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
