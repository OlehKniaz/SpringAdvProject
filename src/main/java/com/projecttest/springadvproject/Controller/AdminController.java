package com.projecttest.springadvproject.Controller;

import com.projecttest.springadvproject.entities.Article;
import com.projecttest.springadvproject.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ArticleService articleService;

    public AdminController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/unApproved")
    public String unApproved(Model model) {
        List<Article> unApproved = articleService.getUnApproved();
        model.addAttribute("articles", unApproved);
        return "unApprovedArticles";
    }

    @GetMapping("/{id}")
    public String changeApproveStatus(Model model,
                                      @PathVariable int id){
        Article save = articleService.setAccssesApproved(id);
        model.addAttribute("set",save);
        return "unApprovedArticles";
    }
}
