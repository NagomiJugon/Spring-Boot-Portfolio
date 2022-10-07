package com.example.mail.app.mail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mail.component.SessionData;
import com.example.mail.entity.Tweet;
import com.example.mail.service.TweetService;

@Controller
@RequestMapping("/mail")
public class MailController {

  private final TweetService tweetService;
  private final SessionData sessionData;

  @Autowired
  public MailController(TweetService tweetService, SessionData sessionData) {
    this.tweetService = tweetService;
    this.sessionData = sessionData;
  }

  @Autowired
  private HttpSession httpSession;

  @GetMapping("/index")
  public String index(Model model,
      TweetForm tweetForm,
      @ModelAttribute("result") String result) {
    List<Tweet> list = new ArrayList<Tweet>();
    list = tweetService.findAll();
    model.addAttribute("list", list);
    model.addAttribute("user_name", sessionData.getUsers().get(httpSession.getId()).getName());
    return "mail/index";
  }

  @PostMapping("/index")
  public String index() {
    return "mail/index";
  }

  @PostMapping("/post")
  public String post(
      @Validated TweetForm tweetForm,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("result", "Please input in 300 characters.");
      return "redirect:/mail/index";
    }
    Tweet tweet = new Tweet();
    tweet.setName(sessionData.getUsers().get(httpSession.getId()).getName());
    tweet.setContent(tweetForm.getContent());
    tweet.setCreated(LocalDateTime.now());
    tweetService.save(tweet);

    return "redirect:/mail/index";
  }

  @PostMapping("/edit/{id}")
  public String edit(
      @Validated TweetForm tweetForm,
      BindingResult result,
      @PathVariable String id,
      Model model,
      RedirectAttributes redirectAttributes) {

    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("result", "Please input in 300 characters.");
      return "redirect:/mail/index";
    }

    int tweetId = Integer.parseInt(id);

    Tweet tweet = new Tweet();
    tweet.setId(tweetId);
    tweet.setContent(tweetForm.getContent());
    tweet.setCreated(LocalDateTime.now());

    tweetService.updateTweet(tweet);

    return "redirect:/mail/index";
  }

  @PostMapping("/delete/{id}")
  public String delete(
      TweetForm tweetForm,
      Model model,
      @PathVariable String id) {
    int tweetId = Integer.parseInt(id);

    Tweet tweet = new Tweet();
    tweet.setId(tweetId);

    tweetService.deleteTweet(tweet);

    return "redirect:/mail/index";
  }

}
