package com.userapp.springmvc.controllers;

import com.userapp.springmvc.dao.UserIService;
import com.userapp.springmvc.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    User user= new User();

    @Autowired
    UserIService userService;

    @RequestMapping("/users")
    public String listarEstudiantes(Model modelo, HttpServletRequest req){
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            return "redirect:/";
        }

        List<User> listUsers= userService.list();
        modelo.addAttribute("user", user);
        modelo.addAttribute("userList", listUsers);
        modelo.addAttribute("userSession", session.getAttribute("username"));
        modelo.addAttribute("rolSession", session.getAttribute("rol"));

        return "users";
    }



    @RequestMapping(value="/save",params={"registrar"})
    public String registrarEstudiantes(@ModelAttribute User user, HttpServletRequest req){
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            return "redirect:/";
        }
        if(user.getId()!=null){
            String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            userService.update(new User(user.getId(),user.getUsername(),password,user.getRol()));
        }else{
            String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            userService.save(new User(user.getUsername(),password,user.getRol()));
        }
        return "redirect:/users";
    }

    @RequestMapping(value="/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, HttpServletRequest req){
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            return "redirect:/";
        }
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping(value="/update")
    @ResponseBody
    public User updateUser(Integer id,Model model){
        return userService.get(id);
    }


}
