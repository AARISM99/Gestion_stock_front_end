package com.example.scolarite.Controller;

import com.example.scolarite.Model.Article;

public interface ICArticle {
  void createArticle();
  void updateArticle();
  Article getAllArticles();
  void deleteArticle();
  Article getOneArticle();
}
