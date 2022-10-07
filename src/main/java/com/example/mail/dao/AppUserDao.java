package com.example.mail.dao;

import com.example.mail.entity.AppUser;

public interface AppUserDao {

  void insertAppUser(AppUser appUser);

  AppUser findAppUserByName(AppUser appUser);

}
