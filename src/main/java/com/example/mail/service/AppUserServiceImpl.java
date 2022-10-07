package com.example.mail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.mail.dao.AppUserDao;
import com.example.mail.entity.AppUser;

@Service
public class AppUserServiceImpl implements AppUserService {

  private final AppUserDao dao;

  @Autowired
  public AppUserServiceImpl(AppUserDao dao) {
    this.dao = dao;
  }

  @Override
  public void save(AppUser appUser) {
    dao.insertAppUser(appUser);
  }

  @Override
  public int auth(AppUser appUser) {
    try {
      AppUser foundAppUser = new AppUser();
      foundAppUser = dao.findAppUserByName(appUser);

      if (appUser.getPassword().equals(foundAppUser.getPassword())) {
        return foundAppUser.getId();
      } else {
        return -1;
      }
    } catch (EmptyResultDataAccessException e) {
      return -2;
    }
  }

}
