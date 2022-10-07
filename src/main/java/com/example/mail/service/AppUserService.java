package com.example.mail.service;

import com.example.mail.entity.AppUser;

public interface AppUserService {

  void save(AppUser appUser);

  int auth(AppUser appUser);

}
