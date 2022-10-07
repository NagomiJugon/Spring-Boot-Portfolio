package com.example.mail.app.mail;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mail.component.SessionData;
import com.example.mail.entity.AppUser;
import com.example.mail.service.AppUserService;

@Controller
public class AuthController {

  private final AppUserService appUserService;
  private final SessionData sessionData;

  @Autowired
  public AuthController(AppUserService appUserService, SessionData sessionData) {
    this.appUserService = appUserService;
    this.sessionData = sessionData;
  }

  @Autowired
  private HttpSession httpSession;

  @Autowired
  private HttpServletRequest request;

  @GetMapping("/signIn")
  public String signIn(Model model, AppUserForm appUserForm, @ModelAttribute("result") String result) {
    return "sign_in";
  }

  @GetMapping("/signUp")
  public String signUp(Model model, AppUserForm appUserForm) {
    return "sign_up";
  }

  @PostMapping("/createUser")
  public String createUser(@Validated AppUserForm appUserForm,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      model.addAttribute("appUserForm", appUserForm);
      return "sign_up";
    }
    AppUser appUser = new AppUser();
    appUser.setName(appUserForm.getName());
    appUser.setPassword(appUserForm.getPassword());
    appUser.setCreated(LocalDateTime.now());

    appUserService.save(appUser);
    redirectAttributes.addFlashAttribute("result", "Registered!");
    return "redirect:/signIn";
  }

  @GetMapping("/auth")
  public String authenticateUser() {
    return "redirect:/signIn";
  }

  @PostMapping("/auth")
  public String authenticateUser(@Validated AppUserForm appUserForm,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      model.addAttribute("appUserForm", appUserForm);
      return "sign_in";
    }
    AppUser appUser = new AppUser();
    appUser.setName(appUserForm.getName());
    appUser.setPassword(appUserForm.getPassword());

    /**
     * appUserService.auth(appUser)の戻り値
     * 1以上：ログインに成功したユーザーのID
     * -1：パスワード不一致
     * -2：検索0件
     */
    int id = appUserService.auth(appUser);
    switch (id) {
      case -2:
        redirectAttributes.addFlashAttribute("result", "Incorrect user name");
        return "redirect:/signIn";
      case -1:
        redirectAttributes.addFlashAttribute("result", "Incorrect password");
        return "redirect:/signIn";
      default:
        httpSession = request.getSession();
        sessionData.setUsers(httpSession.getId(), appUser);
        return "redirect:/mail/index";
    }
  }

  @GetMapping("/logout")
  public String logout() {
    sessionData.signOut(httpSession.getId());
    return "redirect:/signIn";
  }
}
