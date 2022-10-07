package com.example.mail.dao;

import java.util.List;

import com.example.mail.entity.Tweet;

public interface TweetDao {

  void insertTweet(Tweet tweet);

  List<Tweet> findAllTweet();

  void updateTweet(Tweet tweet);

  void deleteTweet(Tweet tweet);

}
