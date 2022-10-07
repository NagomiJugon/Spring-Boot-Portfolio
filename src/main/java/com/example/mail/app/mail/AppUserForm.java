package com.example.mail.app.mail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AppUserForm {

  @Size(min = 1, max = 20, message = "Please input User name in 20 characters or less")
  private String name;

  @NotNull
  @Size(min = 1, max = 20, message = "Please input Password in 20 characters or less")
  private String password;

  public AppUserForm() {
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return String return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

}
