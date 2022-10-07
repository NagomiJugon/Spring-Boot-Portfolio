package com.example.mail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.mail.component.MailFilter;
import com.example.mail.component.SessionData;

@Configuration
public class MailMvcConfig implements WebMvcConfigurer {

  private final SessionData sessionData;

  @Autowired
  public MailMvcConfig(SessionData sessionData) {
    this.sessionData = sessionData;
  }

  @Bean
  public FilterRegistrationBean<MailFilter> loggingFilter() {
    FilterRegistrationBean<MailFilter> registrationBean = new FilterRegistrationBean<>();

    System.out.println("loggingFilter() is called");

    registrationBean.setFilter(new MailFilter(sessionData));
    registrationBean.setOrder(1);
    registrationBean.addUrlPatterns("/mail/*");

    return registrationBean;
  }
}
