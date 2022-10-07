package com.example.mail.dao;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.mail.entity.AppUser;

@Repository
public class AppUserDaoImpl implements AppUserDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public AppUserDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void insertAppUser(AppUser appUser) {
    jdbcTemplate.update("INSERT INTO app_user(name, password, created) VALUES(?, ?, ?)",
        appUser.getName(), appUser.getPassword(), appUser.getCreated());
  }

  @Override
  public AppUser findAppUserByName(AppUser appUser) {
    String sql = "SELECT id, name, password, created FROM app_user WHERE name = ?";
    Map<String, Object> result = jdbcTemplate.queryForMap(sql, appUser.getName());
    AppUser foundAppUser = new AppUser();
    foundAppUser.setId((int) result.get("id"));
    foundAppUser.setName((String) result.get("name"));
    foundAppUser.setPassword((String) result.get("password"));
    foundAppUser.setCreated(((Timestamp) result.get("created")).toLocalDateTime());

    return foundAppUser;
  }

}
