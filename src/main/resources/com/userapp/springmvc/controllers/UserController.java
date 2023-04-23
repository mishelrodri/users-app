package com.userapp.springmvc.controllers;

import com.userapp.springmvc.dao.UserIService;
import com.userapp.springmvc.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    User user= new User();

    @Autowired
    UserIService userService;

    @RequestMapping("/users")
    public String listarEstudiantes(Model modelo){
        List<User> listUsers= userService.list();
        modelo.addAttribute("user", user);
        modelo.addAttribute("userList", listUsers);
        return "users";
    }



    @RequestMapping(value="/save",params={"registrar"})
    public String registrarEstudiantes(@ModelAttribute User user){
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        userService.save(new User(user.getUsername(),password,user.getRol()));
        return "redirect:/users";
    }

    @RequestMapping(value="/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value="/update/{id}")
    public String ediatrEstudiantes(@PathVariable("id") Integer id,Model model){
        user= userService.get(id);
        model.addAttribute("user", user);
        return "redirect:/users";
    }

}
