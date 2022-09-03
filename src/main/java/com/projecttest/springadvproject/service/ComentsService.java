package com.projecttest.springadvproject.service;

import com.projecttest.springadvproject.entities.Coments;

import java.security.Principal;
import java.util.List;

public interface ComentsService {
    List<Coments> getAll();
    Coments saveToDB(Coments coments,String text,int id, Principal principal);
    Coments getById(int id);
    List<Coments> getAllByArticle(int id);
}
