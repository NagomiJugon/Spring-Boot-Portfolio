package com.example.mail.app.mail;

import javax.validation.constraints.Size;

public class TweetForm {

  @Size(min = 1, max = 300, message = "Please input in 300 characters.")
  private String content;

  public TweetForm() {
  }

  /**
   * @return String return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }

}
