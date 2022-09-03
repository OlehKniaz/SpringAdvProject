package com.projecttest.springadvproject.service.Impl;

import com.projecttest.springadvproject.dao.ComentDao;
import com.projecttest.springadvproject.entities.Article;
import com.projecttest.springadvproject.entities.Coments;
import com.projecttest.springadvproject.entities.User;
import com.projecttest.springadvproject.service.ArticleService;
import com.projecttest.springadvproject.service.ComentsService;
import com.projecttest.springadvproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

import java.util.List;

@Service
public class ComentsServiceImpl implements ComentsService {
    private final ComentDao comentDao;
    private UserService userService;
    private ArticleService articleService;
    private ComentsService comentsService;

    @Autowired
    public ComentsServiceImpl(ComentDao comentDao) {
        this.comentDao = comentDao;
    }

    @Override
    public List<Coments> getAll() {
        List<Coments> all = comentDao.findAll();
        return all;
    }

    @Override
    public Coments saveToDB(Coments coments, String text, int id, Principal principal) {
        User user = userService.getUsername(principal.getName());
        coments.setUsers(user);
        Article article = articleService.getById(id);
        coments.setCommentedArticle(article);
        coments.setText(text);
        Coments saved = comentDao.save(coments);
        return saved;
    }

    @Override
    public Coments getById(int id) {
        return comentDao.findById(id).orElse(null);
    }

    @Override
    public List<Coments> getAllByArticle(int id) {
        return comentDao.getAllByCommentedArticleId(id);
    }
}
