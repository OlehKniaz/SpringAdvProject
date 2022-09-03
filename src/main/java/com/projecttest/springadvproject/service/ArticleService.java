package com.projecttest.springadvproject.service;

import com.projecttest.springadvproject.entities.Article;
import com.projecttest.springadvproject.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

public interface ArticleService {
    List<Article> getAll();

    void transferFile(MultipartFile file);

    Article saveToDB(Article article, String articleName, String text,
                     MultipartFile img, Principal principal);
    Article setAccssesApproved(int id);

    Article getById(int id);
    List<Article>getApproved( );
    List<Article>getUnApproved( );
    Article getArticleName(String articleName);



}


