package com.example.mail.service;

import java.util.List;

import com.example.mail.entity.Tweet;

public interface TweetService {

  void save(Tweet tweet);

  List<Tweet> findAll();

  void updateTweet(Tweet tweet);

  void deleteTweet(Tweet tweet);

}
