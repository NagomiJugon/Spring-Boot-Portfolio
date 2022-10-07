package com.example.mail.component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class MailFilter implements Filter {

  private final SessionData sessionData;

  @Autowired
  public MailFilter(SessionData sessionData) {
    this.sessionData = sessionData;
  }

  @Autowired
  private HttpSession httpSession;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    System.out.println("MailFilter doFilter() is called");

    /**
     * sessionData.usersのマップにセッションIDが既に登録されている場合はアクセスを許可
     * そうでない場合はサインイン画面にリダイレクト
     */

    httpSession = ((HttpServletRequest) request).getSession();
    if (sessionData.getUsers().get(httpSession.getId()) != null) {
      filterChain.doFilter(request, response);
    } else {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/signIn");
      dispatcher.forward(request, response);
    }
  }

}
