package com.projecttest.springadvproject.Controller;

import com.projecttest.springadvproject.entities.User;
import com.projecttest.springadvproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String homePage() {
        return "Index";
    }

    @PostMapping("/save")
    public String saveUser(Model model,
                           @RequestParam String username,
                           @RequestParam String usermail,
                           @RequestParam String userpassword) {
        User user = new User();
        User save = userService.saveToDB(user, usermail, username, userpassword);
        model.addAttribute("user", save);
        return "login";
    }


}
