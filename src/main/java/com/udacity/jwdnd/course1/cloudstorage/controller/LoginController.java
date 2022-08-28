package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(@ModelAttribute("userLogin") User userLogin, Model model) {
        System.out.println("UserGetMapping" + userLogin.getUsername());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkUserLogin(@ModelAttribute("userLogin") User userLogin, Model model) {
        System.out.println("User" + userLogin.getUsername());
        return "login";
    }
}
