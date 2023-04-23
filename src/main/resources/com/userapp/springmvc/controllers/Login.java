package com.userapp.springmvc.controllers;

import com.userapp.springmvc.dao.UserIService;
import com.userapp.springmvc.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Login {

    User user= new User();
    @Autowired
    UserIService userService;

    @RequestMapping("/")
    public String login(Model model, HttpServletRequest req){
        model.addAttribute("user",user);
        HttpSession session = req.getSession();
        session.invalidate();
        return "login";
    }

    @RequestMapping(value="/login")
    public String updateUser(@ModelAttribute User user, HttpServletRequest req){

        User usuario= userService.verification(user.getUsername());
        if(usuario==null){
            return "redirect:/";
        }
        if (BCrypt.checkpw(user.getPassword(), usuario.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("rol", usuario.getRol());
            return "redirect:/users";
        }else{
            return "redirect:/";
        }
    }

}
