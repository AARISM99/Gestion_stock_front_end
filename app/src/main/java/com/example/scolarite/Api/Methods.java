package com.example.scolarite.Api;

import com.example.scolarite.Model.Article;
import com.example.scolarite.Model.Categories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface Methods {
  @GET("articles")
  Call <List<Article>> getAll();
  @POST("articles")
  Call <Article> postArticle(@Body Article article);
  @GET("categories")
  Call <List<Categories>> getAllCatgories();
  @POST("categories")
  Call <Categories> postCategorie(@Body Categories categories);
  @GET("articles/iddes")
  Call <List<Article>> filter(@Query("iddes") Integer iddes);
}
