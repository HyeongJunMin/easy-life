package com.example.easylife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

  @GetMapping("/login")
  public String login(@RequestParam(required = false) String errorCode,
                      @RequestParam(required = false) String exceptionCode, Model model) {
    transferErrorInfo(model, errorCode, exceptionCode);
    return "/auth/login";
  }

  private void transferErrorInfo(Model model, String errorCode, String exceptionCode) {
    model.addAttribute("errorCode", errorCode);
    model.addAttribute("exceptionCode", exceptionCode);
  }

}
