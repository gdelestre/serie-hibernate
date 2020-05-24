package fr.springboot.serie_hibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    public LoginController() {
    }

    @RequestMapping({"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping({"/403"})
    public String Error403() {
        return "acces-denied";
    }
}
