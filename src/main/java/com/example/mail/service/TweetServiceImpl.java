package com.example.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mail.dao.TweetDao;
import com.example.mail.entity.Tweet;

@Service
public class TweetServiceImpl implements TweetService {

  private final TweetDao dao;

  @Autowired
  public TweetServiceImpl(TweetDao dao) {
    this.dao = dao;
  }

  @Override
  public void save(Tweet tweet) {
    dao.insertTweet(tweet);
  }

  @Override
  public List<Tweet> findAll() {
    return dao.findAllTweet();
  }

  @Override
  public void updateTweet(Tweet tweet) {
    dao.updateTweet(tweet);
  }

  @Override
  public void deleteTweet(Tweet tweet) {
    dao.deleteTweet(tweet);
  }

}
