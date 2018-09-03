package com.styoanovst.task.farmingmanagementsystem.controller;

import com.styoanovst.task.farmingmanagementsystem.dto.UserDto;
import com.styoanovst.task.farmingmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(HttpServletResponse response, UserDto userDto) throws IOException {
        try {
            userService.registerUser(userDto);
        } catch (Exception e) {
            response.sendRedirect("/register?error");
            e.printStackTrace();
        }
        response.sendRedirect("/login?registered");
    }

}
