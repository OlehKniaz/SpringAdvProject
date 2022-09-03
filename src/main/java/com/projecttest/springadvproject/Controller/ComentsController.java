package com.projecttest.springadvproject.Controller;

import com.projecttest.springadvproject.entities.Article;
import com.projecttest.springadvproject.entities.Coments;
import com.projecttest.springadvproject.entities.User;
import com.projecttest.springadvproject.service.ArticleService;
import com.projecttest.springadvproject.service.ComentsService;
import com.projecttest.springadvproject.service.UserService;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/coments")
public class ComentsController {
    private ComentsService comentsService;
    private ArticleService articleService;
    private UserService userService;

    public ComentsController(ArticleService articleService, UserService userService, ComentsService comentsService) {
        this.articleService = articleService;
        this.userService = userService;
        this.comentsService = comentsService;

    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        List<Coments> byId = comentsService.getAllByArticle(id);
        model.addAttribute("coment", byId);
        return "Index";
    }

    @GetMapping("/post/{id}")
    public String saveComent(Model model,
                             @RequestParam String text,
                             @PathVariable int id,
                             Principal principal) {
        Coments coments = new Coments();
        Coments save =comentsService.saveToDB(coments,text,id,principal);
        return "Index";
    }
}
