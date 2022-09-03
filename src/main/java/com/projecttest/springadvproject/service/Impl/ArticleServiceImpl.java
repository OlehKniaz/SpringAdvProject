package com.projecttest.springadvproject.service.Impl;

import com.projecttest.springadvproject.dao.ArticleDao;
import com.projecttest.springadvproject.entities.Article;
import com.projecttest.springadvproject.entities.User;
import com.projecttest.springadvproject.service.ArticleService;
import com.projecttest.springadvproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;
    private ArticleService articleService;
    private UserService userService;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao1) {
        this.articleDao = articleDao1;

    }

    @Override
    public List<Article> getAll() {
        List<Article> all = articleDao.findAll();
        return all;
    }

    @Override
    public void transferFile(MultipartFile file) {
        String pathToFolder = System.getProperty("user.home") + File.separator + "images" + File.separator;

        try {
            file.transferTo(new File(pathToFolder + file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Article saveToDB(Article article, String articleName, String text,
                            MultipartFile img, Principal principal) {
        User user = userService.getUsername(principal.getName());
        article.setUser(user);
        article.setArticleName(articleName);
        article.setText(text);
        article.setApproved(false);
        articleService.transferFile(img);
        article.setImg(img.getOriginalFilename());

        Article saved = articleDao.save(article);
        return saved;
    }

    @Override
    public Article setAccssesApproved( int id) {
        Article set1 = articleService.getById(id);
        set1.setApproved(true);
        return set1;
    }

    @Override
    public Article getById(int id) {
        return articleDao.findById(id).orElse(null);
    }

    @Override
    public List<Article> getApproved() {
        List<Article> approved = articleDao.getArticleApproved();
        return approved;
    }

    @Override
    public List<Article> getUnApproved() {
        List<Article> approved = articleDao.getArticleUnApproved();
        return approved;
    }

    @Override
    public Article getArticleName(String articleName) {
        Article byArticleName = articleDao.getByArticleWithoutOptinal(articleName);
        return byArticleName;
    }

}
