package com.example.mail.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.mail.entity.Tweet;

@Repository
public class TweetDaoImpl implements TweetDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public TweetDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void insertTweet(Tweet tweet) {
    String sql = "INSERT INTO tweets(user_name, content, created) VALUES(?, ?, ?)";
    jdbcTemplate.update(sql, tweet.getName(), tweet.getContent(), tweet.getCreated());
  }

  @Override
  public List<Tweet> findAllTweet() {
    String sql = "SELECT * FROM tweets";
    List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);

    List<Tweet> list = new ArrayList<Tweet>();

    for (Map<String, Object> result : resultList) {
      Tweet tweet = new Tweet();
      tweet.setId((int) result.get("id"));
      tweet.setName((String) result.get("user_name"));
      tweet.setContent((String) result.get("content"));
      tweet.setCreated(((Timestamp) result.get("created")).toLocalDateTime());

      list.add(tweet);
    }
    return list;
  }

  @Override
  public void updateTweet(Tweet tweet) {
    String sql = "UPDATE tweets SET content = ?, created = ? WHERE id = ?";
    jdbcTemplate.update(sql, tweet.getContent(), tweet.getCreated(), tweet.getId());
  }

  @Override
  public void deleteTweet(Tweet tweet) {
    String sql = "DELETE FROM tweets WHERE id = ?";
    jdbcTemplate.update(sql, tweet.getId());
  }

}