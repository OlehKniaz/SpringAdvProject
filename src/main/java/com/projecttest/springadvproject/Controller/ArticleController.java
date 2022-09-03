package com.projecttest.springadvproject.Controller;

import com.projecttest.springadvproject.entities.Article;
import com.projecttest.springadvproject.entities.Coments;
import com.projecttest.springadvproject.entities.User;
import com.projecttest.springadvproject.service.ArticleService;
import com.projecttest.springadvproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.security.Principal;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;
    private UserService userService;

    public ArticleController(ArticleService articleService,UserService userService){
        this.articleService = articleService;
        this.userService=userService;
    }

    @GetMapping("/{id}")
    public String getById (Model model ,@PathVariable int id){
        Article byId = articleService.getById(id);
        model.addAttribute("articles",byId);
        return "ArticleDetails";
    }
    @PostMapping("/save")
    public String saveArticle(Model model,
                              @RequestParam String articleName,
                              @RequestParam String text,
                              @RequestParam MultipartFile img,
                              Principal principal) {
        Article article = new Article();
        Article save = articleService.saveToDB(article,articleName,text,img,principal);
        model.addAttribute("article",save);
        return "Index";
    }

    @GetMapping("/Approved")
    public String Approved(Model model){
        List<Article>approved = articleService.getApproved();
        model.addAttribute("article",approved);
        return "Index";
    }

}
